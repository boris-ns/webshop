
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
	Aplikacija sama menja kategoriju kupca na osnovu broja kupovina ili potrošene sume novca
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
	- [ ] uracunavanje sezonskog popusta
	- [ ] uracunavanje popusta na kategoriju
	- [ ] uracunavanje popusta za ceste kupce
	- [x] uracunavanje popusta na osnovu kupona
	- [ ] obavestiti prodavca ako je količina artikala u magacinu ispod određene vrednosti

- Sistem preporuke za kupovinu artikala
	- [ ] Preporuciti artikle iz kategorije za koju korisnik ima najvise porudzbina
	- [x] Preporuciti artikle koji su na snizenju
	- [ ] Ako preko 60% korisnikovih narudzbina ima besplatnu postarinu, preporuciti mu artikle sa besplatnom postarinom (ovo vazi samo za korisnike sa REGULAR kategorijom) 

- računanje svih popusta prilikom kupovine: klasičan popust na artikal, popust na količinu, sezonski popust, popust na kategoriju, popust za česte kupce kod jednog prodavca i popust na osnovu kupona. Ovde bi se vodilo računa da ukupni popust ne može da pređe neku predefinisanu vrednost, a i da se ne mogu baš sve vrste popusta iskoristiti istovremeno.

## Primer rezonovanja za kupovinu artikla:
- korisnik filtrira i pretrazuje artikle
- kada naiđe na artikal koji želi, naručuje ga
- pokreće se rezoner koji će da računa cenu tog artikla za datog korisnika na osnovu njegovih ostvarenih popusta i ostalih popusta koji postoje za artikal
- rezoner vraća konačnu cenu korisniku
- korisnik potvrđuje kupovinu
- pokreće se pravilo za kupovinu koje treba da kasnije aktivira pravila za promenu kategorije kupca i pravilo za obaveštavanje prodavca ako je broj artikala u magacinu manji od predefinisane vrednosti čime postižemo forward chaining.


