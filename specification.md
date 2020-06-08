# SISTEMI BAZIRANI NA ZNANJU SIIT - PREDLOG PROJEKTA

## Članovi tima:
Boris Šuličenko, SW 4/2016

## Opis problema:
Motivacija za razvoj webshop-a baziranog na pravilima je što se način poslovanja može lako menjati jer će većina poslovne logike biti realizovana kroz pravila. Ovo nam omogućava da ne mora eksplicitno programer menjati pravila poslovanja, već to može da uradi i domenski ekspert.

Problem koji ovaj projekat rešava je webshop koji radi po uzoru na AliExpress. Cela aplikacija bi predstavljala jednu platformu na kojoj mogu da se registruju kupci i prodavci (koji predstavljaju različite prodavnice). Aplikacija bi imala 3 korisničke uloge: kupac, prodavac i admin.

Kupac ima mogućnost da: pretražuje i filtrira artikle po kategoriji i ceni. Kada završi sa pretragom kupac može da potvrdi kupovinu nakon koje se pokreće rezoner koji računa sve popuste na artikle i vraća korisniku konačnu cenu. Nakon izvršene kupovine rezoner će proveriti stanje artikala u magacinu i obavestiće prodavca ako je taj broj manji od prethodno definisane vrednosti. Kupac takođe ima i uvid u sve svoje porudžbine.
Prodavac može da dodaje/menja/briše artikle. Prilikom dodavanja i izmene se zadaju mogući popusti poput klasičnog popusta, popusta na količinu, popusta za kupce koji često kupuju kod njega i mogućnost korišćenja kupona.

Admin može da dodaje/menja/briše prodavce. Može da kreira sezonske popuste (npr. black friday) i popuste na određenu kategoriju artikla.

Ulaz u sistem predstavljaju artikli koji se kupuju sa njihovim informacijama o popustu i podaci o kupcu sa njegovim osobinama koje takođe utiču na popust. Kao izlaz se očekuje izračunati popust na dati artikal.

U sistemu neće postojati predefinisana baza znanja, već će se ona vremenom popunjavati sa podacima o korisnicima i artiklima, tj. rezonovanje isključivo zavisi od kupca i artikla koji kupuje i eventualno njegovih prethodnih kupovina ako je prodavac omogućio popust za kupce koji često kupuju od njega.
	
## Pravila
- Klasifikacija korisnika  
	Aplikacija sama menja kategoriju kupca na osnovu broja kupovina ili potrošene sume novca.  
	Admin ima mogucnost da generise ova 3 (ili vise) pravila koriscenjem rule template-a i popunjavanjem forme za granicne 
	vrednosti. Posto se ova pravila aktiviraju samo u slucaju kupovine, postoje i implementirana defaultna pravila
	koja ce se koristiti ukoliko admin eksplicitno ne kreira svoja pravila. 
	- [x] kupac ce postati SILVER ako je trenutna kategorija REGULAR i ako je broj narudzbina 50
	- [x] kupac ce postati GOLD ako je trenutna kategorija SILVER, broj narudzbina 100 i suma potrosenog novca 3000$
	- [x] kupac ce postati DIAMOND ako je trenutna kategorija GOLD, broj narudzbina 500 i suma potrosenog novca 8000$

- Popusti prilikom kupovine
	- [x] kupci sa kategorijom GOLD imaju popust na sve artikle od 5%
	- [x] kupci sa kategorijom DIAMOND imaju popust na sve artikle od 10%
	- [x] kupci sa kategorijom SILVER, GOLD i DIAMOND ne plaćaju poštarinu
	- [x] uracunavanje popusta samog artikla
	- [x] uracunavanje popusta na kolicinu
	- [x] racunanje nove cene ako se narucuje vise od jednog proizvoda
	- [x] uracunavanje popusta za ceste kupce (ako je kupac narucio 10 ili vise artikala iz jedne prodavnice, dobice popust koji je definisan prilikom registracije prodavnice)
	- [x] uracunavanje popusta na osnovu kupona
	- [ ] uracunavanje sezonskog popusta - admin kreira sezonski popust, gde zadaje od kad do kad ce on vaziti
	- [ ] uracunavanje popusta na kategoriju - admin kreira poopust na kategoriju gde zadaje od kad do kad ce on vaziti
	- [x] ograniciti popust na maksimalnu zadatu vrednost (ovo je napravljeno kao zasebno pravilo koje se aktivira nakon svake promene popusta, realizovano je kao zasebno pravilo tako da se ne mora voditi racuna o maksimalnom popustu tokom kreiranja ostalih pravila - uslovi ce biti jednostavniji)
	- [x] zavrsavanje kupovine - pravilo koje ce se pretposlednje aktivirati i izvrsice racunanje konacne cene proizvoda i smanjice broj artikala u magacinu, sto ce dovesti do aktivacije pravila ispod (obavestavanje prodavca)
	- [x] obavestiti prodavca ako je količina artikala u magacinu ispod određene vrednosti - ovo pravilo se aktivira poslednje u grupi pravila za kupovinu i sluzi da postavi flag na true ako je broj artikala pao ispod 20

- Sistem preporuke za kupovinu artikala
	Sistem preporuke se sastoji od pravila koja se izvrsavaju u nizu (koriscenjem 'salience' atributa) i sluze kao filter za sve dostupne artikle. U svakom sledecem koraku (pravilu) lista preporuka ce se smanjiti ukoliko su uslovi ispunjeni. Izlaz iz poslednjeg pravila predstavlja konacnu listu preporucenih artikala.
	- [x] Preporuciti artikle iz kategorije kojoj je pripadala poslednja korisnikova porudzbina 
	- [x] Preporuciti artikle koji su na snizenju
	- [x] Ako bar 60% korisnikovih narudzbina ima besplatnu postarinu, preporuciti mu artikle sa besplatnom postarinom (ovo vazi samo za korisnike sa REGULAR kategorijom) 
	- [x] Preporuciti artikle po cenovnom rangu - naci srednju vrednost korisnikovih porudzbina i preporuciti artikle koji imaju cenu u rangu +-20% od srednje vrednosti
	- [x] Preporuciti artikle koji se mogu kupovati na veliko - u listi preporuka ce se naci artikli koji se mogu kupovati na veliko ako je korisnik u proslosti narucio bar 70% artikala na veliko
	- [x] Preporuciti artikle koji je ostalo malo u prodavnici (ovo pravilo se izvrsava poslenje u nizu pravila za preporuke, ako je ostalo bar 40% artikala ciji je kvantitet manji ili jednak od 20, zanemaricemo sve ostale artikle i preporucicemo samo ove)

- računanje svih popusta prilikom kupovine: klasičan popust na artikal, popust na količinu, sezonski popust, popust na kategoriju, popust za česte kupce kod jednog prodavca i popust na osnovu kupona. Ovde bi se vodilo računa da ukupni popust ne može da pređe neku predefinisanu vrednost, a i da se ne mogu baš sve vrste popusta iskoristiti istovremeno.

## Interakcija pravila

Pravilo koje sluzi za ogranicavanje popusta na maksimalnu zadatu vrednost se aktivira nakon svake promene popusta i njegov cilj je da postavi vrednost popusta na maksimalnu dozvoljenu ukoliko je granica predjena. Ovo pravilo je realizovano kao zasebno pravilo da se ne mora voditi racuna o maksimalnom popustu tokom kreiranja ostalih pravila cime postizemo jednostavnije uslove.

Pravilo za zavrsavanje kupovine se aktivira kao pretposlednje u nizu pravila za racunanje popusta. Njegov zadatak je da izvrsi racunanje konacne cene proizvoda i da smanji broj dostupnih artikala u magacinu. Smanjenje broja artikala dovodi do aktivacije sledeceg, poslednjeg, pravila - obavestavanje prodavca ako je kolicina artikala u magacinu ispod odredjene vrednosti.

Sistem preporuke se sastoji od pravila koja se izvrsavaju u nizu (koriscenjem 'salience' atributa) i sluze kao filter za sve dostupne artikle. U svakom sledecem koraku (pravilu) lista preporuka ce se smanjiti ukoliko su uslovi ispunjeni. Izlaz iz poslednjeg pravila predstavlja konacnu listu preporucenih artikala.

## Primer rezonovanja za kupovinu artikla:
- korisnik filtrira i pretrazuje artikle
- kada naiđe na artikal koji želi, naručuje ga
- pokreće se rezoner koji će da računa popuste artikla na osnovu klase kupca i samog artikla
- nakon izvrsavanja pravila za racunanje popusta, pokrecu se pravila za racunanje nove cene, smanjivanje kolicina artikala u magacinu i obavestavanje prodavca ako su zalihe artikla male
- nako izvrsavanja grupe pravila za kupovinu aktivira se grupa pravila za promenu kategorija kupca
- rezoner vraca korisniku originalnu cenu, novu cenu i ostvareni popust
