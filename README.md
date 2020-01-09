# Autobuska stanica
Ovo je mala web aplikacija za autobusku stanicu. Moguce je pregledati dostupne linije sa podacima o prevozniku, destinaciji, broju mesta, vremenu polaska i ceni karte. Linije je moguce dodavati, editovati i brisati. Za svaku liniju je moguce rezervisati kartu. Implementirano je pretrazivanje linija po imenu prevoznika, desinaciji i/ili ceni karte.

## Napomene
Pre pokretanje aplikacije potrebno je pokrenuti ddl.sql skriptu iz foldera sql u programu MySQL kako bi se generisala baza podataka za aplikaciju. Projekat je radjen sa MySQL verzijom 8, i dependency za tu verziju je podesen u pom.xml fajlu, ukoliko imate instaliranu drugu verziju potrebno je da editujete pom.xml fajl i da unesete zeljenu verziju. Ukoliko zelite da radite sa postojecom bazom onda u applicaion.properties podesite parametre za zeljenu bazu. 

## Pokretanje aplikacije
U komandnom prozoru otvoriti korijensku datoteku projekta i pokrenuti komandu: <br>
<b>mvn spring-boot:run</b>
<br>
Aplikacija se moze pokrenuti i iz Eclipse IDE.

## Koristene tehnologija
<li>Baza podataka: MySQL
<li>Backend: Spring Boot
<li>Perzistencija podataka: Hibernate
<li>Frontend: AngularJS


<i><b>Ova aplikacija je napravljena u sklopu pripreme za zavrsni test kursa Java Web Development koji organizuje FTN Informatika doo.</b></i>