# Programmentwurf - AutoChef

Name: Schirmer, Oliver \
Martrikelnummer: XXX

Name: Müller, Luca \
Martrikelnummer: XXX

Abgabedatum: 28. Mai 2023

## Inhaltsverzeichnis

- [Einführung](#1-einführung)
    - [Übersicht über die Applikation](#11-übersicht-über-die-applikation)
    - [Wie startet man die Applikation?](#12-wie-startet-man-die-applikation)
    - [Wie testet man die Applikation?](#13-wie-testet-man-die-applikation)
- [Clean Architecture](#2-clean-architecture)
    - [Was ist Clean Architecture?](#21-was-ist-clean-architecture)
    - [Analyse der Dependency Rule](#22-analyse-der-dependency-rule)
    - [Analyse der Schichten](#23-analyse-der-schichten)
- [SOLID](#3-solid)
    - [Analyse Single-Responsibility-Principle (SRP)](#31-analyse-single-responsibility-principle-srp)
    - [Analyse Open-Closed-Principle (OCP)](#32-analyse-open-closed-principle-ocp)
    - [Analyse Dependency-Inversion-Principle (DIP)](#33-analyse-dependency-inversion-principle-dip)
- [Weitere Prinzipien](#4-weitere-prinzipien)
    - [Analyse GRASP: Geringe Kopplung](#41-analyse-grasp-geringe-kopplung)
    - [Analyse GRASP: Hohe Kohäsion](#42-analyse-grasp-hohe-kohäsion)
    - [Don't repeat yourself (DRY)](#43-dont-repeat-yourself-dry)
- [Unit Tests](#5-unit-tests)
    - [10 Unit Tests](#51-10-unit-tests)
    - [ATRIP: Automatic](#52-atrip-automatic)
    - [ATRIP: Thorough](#53-atrip-thorough)
    - [ATRIP: Professional](#54-atrip-professional)
    - [Code Coverage](#55-code-coverage)
    - [Fakes und Mocks](#56-fakes-und-mocks)
- [Domain Driven Design](#6-domain-driven-design)
    - [Ubiquitous Language](#61-ubiquitous-language)
    - [Entities](#62-entities)
    - [Value Objects](#63-value-objects)
    - [Repositories](#64-repositories)
    - [Aggregates](#65-aggregates)
- [Refactoring](#7-refactoring)
    - [Code Smells](#71-code-smells)
    - [2 Refactorings](#72-2-refactorings)
- [Entwurfsmuster](#8-entwurfsmuster)
    - [Entwurfsmuster: Facade](#81-entwurfsmuster-facade)
    - [Entwurfsmuster: Builder](#82-entwurfsmuster-builder)

## 1. Einführung

### 1.1. Übersicht über die Applikation

AutoChef ist eine Anwendung zur einfachen und effizienten Verwaltung und Erstellung von
Essensplänen, sowie dazugehöriger Einkaufslisten. Zugrunde liegt dabei eine Datenbank an Rezepten.
Diese ist beliebig erweiterbar durch eine [Chefkoch](chefkoch.de)-Integration. Diese ermöglicht es,
einen Link zu einem Rezept auf Chefkoch an die Anwendung zu übergeben, woraufhin das
entsprechende Rezept heruntergeladen und persistiert wird. Die Datenbank an Rezepten, sowie jeweilige
Rezept-Details, können jederzeit eingesehen werden. Hauptfunktion ist jedoch die Generierung von
Essensplänen. Dafür kann der Nutzer einen Zeitraum, sowie die Anzahl an Personen je Mahlzeit angeben
und die Anwendung generiert anhand der Rezept-Datenbank einen zufälligen Essensplan. Für diesen
Essensplan wird ebenfalls eine Einkaufsliste generiert, die an die Anzahl an Personen angepasst ist.
Mit diesen Funktionalitäten ist es einfach möglich, eine Woche kulinarisch zu planen und die
Einkaufliste für den Wocheneinkauf zu erstellen.

### 1.2. Wie startet man die Applikation?

Bei AutoChef handelt es sich um eine CLI-Anwendung, geschrieben in Java 19. Zum Starten wird daher
lediglich ein Desktop-Rechner mit **Java 19 aufwärts** benötigt. Um die Anwendung starten zu können, 
muss zunächst das GitHub-Repository geclont werden:

```bash
git clone https://github.com/ncryptedV1/AutoChef
```
Die Anwendung kann dann im nächsten Schritt über ein Konsolenfenster mit dem folgenden Befehl gestartet werden:

```bash
cd AutoChef/
java -jar AutoChef.jar
```

### 1.3. Wie testet man die Applikation?

Nach dem Start der Anwendung wird der Nutzer durch einen intuitiven Dialog-Prozess begrüßt und
geleitet. Der Nutzer interagiert dabei mit der Anwendung mittels des Konsolenfensters. Über dieses
werden sowohl Informationen ausgegeben, als auch Eingaben vom Nutzer eingeholt. Der Dialog-Prozess
ist so gestaltet, dass der Nutzer in der Regel mehrere nummerierte Optionen zur Auswahl hat und nur
die Nummer der gewünschten Option eingeben und mit `Enter` absenden muss. Für den Import von Rezepten
muss sich der Nutzer zuvor ein Rezept von Chefkoch.de aussuchen und den dazugehörigen Link im 
entsprechenden Dialog-Prozess-Schritt einfügen. Zu Beginn der Nutzung ist die
Rezept-Datenbank noch leer, weshalb es sich anbietet anfangs einige Rezepte zu importieren. Erst
danach können die Funktionen der Rezept-Anzeige und Essensplan-Generierung sinnvoll genutzt werden.

Hinweis: Zur Persistierung der Rezepte erstellt die Anwendung im aktuellen Arbeitsverzeichnis (des
Konsolenfensters) einen `recipes`-Ordner. Falls der Nutzer keine Rechte hat im aktuellen
Arbeitsverzeichnis Ordner & Dateien zu erstellen, sowie in diese zu schreiben, kann das Programm
nicht ordnungsgemäß arbeiten und terminiert. Versuche in dem Fall die Anwendung mit erhöhten
Berechtigungen zu starten oder in einem Arbeitsverzeichnis mit Schreibzugriff auszuführen.

## 2. Clean Architecture

### 2.1. Was ist Clean Architecture?

Clean Architecture ist eine Architektur- und Designphilosophie, die darauf abzielt, komplexe
Softwaresysteme in leicht verständliche, wartbare und erweiterbare Komponenten zu unterteilen. Es
wurde von Robert C. Martin entwickelt und basiert auf den SOLID-Prinzipien.

Im Wesentlichen sieht Clean Architecture vor, dass eine Software in mehrere Schichten unterteilt
wird, wobei jede Schicht eine klare Abhängigkeitshierarchie aufweist und nur von der nächstgelegenen
Schicht abhängt. Die äußerste Schicht ist die Benutzerschnittstelle, die direkt mit dem Benutzer
interagiert, gefolgt von einer oder mehreren Schichten mit Geschäftslogik, Datenzugriff und
Infrastruktur.

Durch diese Aufteilung kann jede Schicht unabhängig von den anderen Schichten getestet und gewartet
werden, was zu einer höheren Flexibilität und Skalierbarkeit des gesamten Systems führt. Darüber
hinaus ist es einfacher, Änderungen an einem Teil des Systems vorzunehmen, ohne Auswirkungen auf den
Rest des Systems zu haben.

Clean Architecture ermutigt auch zur Verwendung von Schnittstellen, um die Abhängigkeiten zwischen
den Komponenten zu minimieren. Durch die Verwendung von Schnittstellen können verschiedene
Implementierungen ausgetauscht werden, ohne dass dies Auswirkungen auf den Rest des Systems hat.

Zusammenfassend lässt sich sagen, dass Clean Architecture eine Methode ist, um große, komplexe
Softwaresysteme in einfachere, leichter zu wartende Komponenten aufzuteilen, indem eine klare
Abhängigkeitshierarchie zwischen den Komponenten eingeführt wird.

### 2.2. Analyse der Dependency Rule

#### Positiv-Beispiel: Dependency Rule

- gewählte Klasse: `DialogService`

![Dependency Rule positives Beispiel UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/dependency-rule-pos.iuml)

`DialogService` ist abhängig von `RecipeRepository` (Interface).
`DialogService` hat keine Abhängigkeiten zu Implementierungen, nur zu Interfaces und anderen
Domänen-Entities/Value Objects.
In dieser Anwendung zeigt die `DialogService`-Klasse ein gutes Beispiel für das Einhalten der
Dependency Rule, da sie nur vom `RecipeRepository`-Interface abhängt und nicht von einer konkreten
Implementierung. Das bedeutet, dass die äußeren Schichten (in diesem Fall die Infrastrukturschicht
mit der `RecipeFileRepository`-Implementierung) von der inneren Schicht (Domänenschicht) abhängen
und nicht umgekehrt. Die Abhängigkeiten richten sich somit von außen nach innen.
Es gibt nur eine Klasse, die von `DialogService` abhängt: `AutoChef`. Dies ist die Main-Klasse der
Anwendung. Ihre Aufgabe ist die Initialisierung des `DialogService` mitsamt
Abhängigkeiten (`RecipeRepository`) und enthält daher keine weitere Logik noch
Konfigurationsdetails. Aus diesem Grund ist sie der Infrastrukturschicht zuzuordnen. Auch hier wird
die Dependency Rule eingehalten, da eine Abhängigkeit von außen nach innen besteht.

#### Negativ-Beispiel: Dependency Rule

- gewählte Klasse: `ChefkochRecipeFetcher`

![Dependency Rule negatives Beispiel UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/dependency-rule-neg.iuml)

`ChefkochRecipeFetcher` ist abhängig von `WebsiteFetcher`.
`WebsiteFetcher` ist eine Util-Klasse, die für das Abrufen von Webseiten-Inhalten zuständig ist und
damit der Infrastruktursschicht zugehörig.
Dadurch verletzt die `ChefkochRecipeFetcher`-Klasse die Dependency Rule, da eine Abhängigkeit von
der Applikationsschicht in die Infrastrukturschicht besteht. Die `ChefkochRecipeFetcher`-Klasse
sollte stattdessen von einem Interface abhängen, das in der Domänenschicht definiert ist und von
einer Implementierung in einer äußeren Schicht bereitgestellt wird, um die Abhängigkeiten korrekt
von außen nach innen zu richten.
Von der Klasse abhängig ist lediglich der `DialogService`, welcher sich auf selbiger Schicht
befindet.

### 2.3. Analyse der Schichten

#### Schicht: Domain Code

- gewählte Klasse: `Recipe`

![Schicht 1 UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/layer-1.iuml)

Die `Recipe`-Klasse ist eine Entity im Sinne der Clean-Architecture, da sie die Idee eines
Rezeptes abbildet. Ein Rezept besteht aus folgenden Attributen:

- `name: String`: Name des Rezeptes
- `groceryList: GroceryList` : Liste an Zutaten, die für das Rezept benötigt werden
- `recipeSteps: List<RecipeStep>`: Liste an Zubereitungsschritten, die im Laufe des Rezeptes
  abgearbeitet werden müssen

Ein Rezept wird eineindeutig über eine ID identifiziert. Die ID umfasst den Namen in Kleinschrift.
Außerdem existieren für die Attribute und die ID jeweils Getter-Methoden und ein Konstruktor.

Damit liegt die Aufgabe der `Recipe`-Entity darin, ein Rezept semantisch im Code zu repräsentieren.
Da das Konzept eines Rezept essenziell für die Domäne von Essensplänen ist, wurde es als Teil des
Kernes der Anwendung aufgenommen. `Recipe` ist deshalb Teil der Schicht "Domain Code", da der
Domänencode ebenjene Entities bzw, den Kern der Anwendung enthalten sollte. Außerdem ändert sich die
Modellierung eines Rezeptes selten, was ebenso dafür spricht, es in die Schicht "Domain Code"
einzuordnen.

#### Schicht: Application-Code

- gewählte Klassen: `DialogService` mit `DialogState`

![Schicht 2 UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/layer-2.iuml)

Die Schicht des Application-Codes umfasst mehrere Klassen. Dabei soll die Klasse `DialogService` im
Fokus stehen.

Im Kern ist der Dialog-Service für die Ablauflogik der Anwendung verantwortlich. Er verwaltet die
Datenpersistenz über die Klassen `RecipeRepository` und `RecipeFileRepository`, ist aber
gleichzeitig auch für die Nutzung von Benutzereingaben über die
Klassen `InputParser` und `OutputService` verantwortlich. Damit
ist er die Schnittstelle zwischen den einzelnen Verantwortungsbereichen der Anwendung.

Im Allgemeinen startet der Service den Dialog mit dem Benutzer, organisiert die Generierung von Essensplänen
und gibt dem Benutzer die Möglichkeit Rezepte hinzuzufügen. In diesem Sinne nimmt er die Rolle
eines "Controllers" ein. Für andere Anwendungen wie etwa eine Web-Anwendung würde eine andere
Funktionalität erwartet werden. Der Dialog-Service ist speziell für den Anwendungsfall einer
CLI-Anwendung definiert und nutzbar.

Ebenso bedeutet das, dass Änderungen an dem DialogService keinen Einfluss auf den Domänen-Code
haben. All diese Aspekte begründen, warum der Dialog-Service im Application Code angesidelt ist.

## 3. SOLID

### 3.1. Analyse Single-Responsibility-Principle (SRP)

#### Positiv-Beispiel

gewählte Klasse: `Quantity`

![Single Responsibility positives Beispiel UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/single-responsibility-pos.iuml)

Die `Quantity`-Klasse bildet eine "Menge" oder "Anzahl" ab. Das ist relevant für die Menge von
Zutaten in einem Rezept. Die Klasse ist maßgeblich definiert durch ihr einziges Attribute `value`,
das eine Menge als `double` repräsentiert. Zusätzlich dazu kann über die Methode `multiply`
eine `Quantity`-Instanz mit einem Wert multipliziert werden, was wieder eine neue `Quantity`-Instanz
zurückgibt. `Quantity` ist vor allem relevant im Kontext von `GroceryItem`, da dort beschrieben
wird, wie viel von einer `Ingredient`-Instanz benötigt wird.

Die Verantwortung der Klasse liegt demnach darin, semantisch eine "Menge" abzubilden. Daneben
besitzt sie keine weiteren Verantwortlichkeiten.

#### Negativ-Beispiel

gewählte Klasse: `DialogService`

![Single Responsibility negatives Beispiel UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/single-responsibility-neg-1.iuml)

Ein Negativbeispiel für das SRP ist der Dialog-Service - im Speziellen die `startMealPlanGeneration`
-Methode. Die Klasse nimmt die Rolle eines "Controllers" ein, da sie die Persistenz von Daten und
die Ausgabe an das Command-Line-Interface verwaltet. Als Teil der Application-Code-Schicht enthält
der Dialog-Service jedwede Logik für den Ablauf der Anwendung:

- es startet den Dialog mit dem Benutzer
- organisiert die Generierung von Essensplänen und
- gibt dem Benutzer die Möglichkeit Rezepte hinzuzufügen.
Der Dialog-Service ist speziell für den Anwendungsfall einer CLI-Anwendung definiert.

Der Dialog-Service hat jedoch mehrere Verantwortlichkeiten und bricht damit das SRP:

- Steuerung der Ablauflogik (dafür werden die Verwaltung von Benutzerein- und -ausgabe als auch der
  Datenpersistenz genutzt)
- Generierung eines Essensplan über die Methode `startMealPlanGeneration`

Gerade die zweite Verantwortlichkeit - die Generierung eines Essenplans - kann in einen separaten
Service ausgelagert werden:

![Single Responsibility negatives Beispiel UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/single-responsibility-negc.iuml)

Dabei könnte `startMealPlanGeneration` mit einem `GenerationService` interagieren.
Der `GenerationService` wäre dann für die eigentliche Generierung des Essensplans verantwortlich,
während die `startMealPlanGeneration` lediglich eine verwaltende Rolle einnehmen würde.

### 3.2. Analyse Open-Closed-Principle (OCP)

#### Positiv-Beispiel

gewählte Klasse: `RecipeFileRepository` mit Interface `RecipeRepository`

![Open-Closed positives Beispiel UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/open-closed-pos.iuml)

Die `RecipeFileRepository`-Klasse ist ein Repository, dass das Interface `RecipeRepository`
implementiert. Das Interface beschreibt, welche Schnittstelle zum Speichern von Daten benötigt wird.
Die `RecipeFileRepository`-Klasse implementiert diese Methoden für den Fall von Datenpersistenz in
Dateien.

Da hier ein Interface verwendet wird, ist es leicht möglich neue Funktionalität hinzuzufügen. Dazu
muss lediglich ein neuer Methodekopf im Interface definiert werden. Die dazugehörige Methode muss
in allen Klassen, die das Interface implementieren, hinzugefügt werden. Damit ist die "Open"
Eigenschaft des OCP erfüllt. Auf der anderen Seite sollte Code "closed" (geschlossen) gegenüber Modifikationen
sein. Das ist ebenso durch die Verwendung eines Interfaces erfüllt. Das Interface bestimmt die
Funktionalitäten der Klasse.

Hier mit dem OCP zu arbeiten ist vor allem aus Sicht der Flexibilität und Erweiterbarkeit sinnhaft.
Sollte sich später dazu entschieden werden, eine andere Methode der Datenpersistenz zu wählen, muss
lediglich die Implementierung des Interfaces angepasst bzw weitere Klassen, die das Interface
implementieren hinzugefügt werden. So kann die Anwendung an verschiedene Umgebungen und
Anforderungen leichter angepasst werden, ohne die inneren Schichten der Clean-Architecture verändern
zu müssen. Gerade in Hinsicht dessen, dass dieses Projekt eventuell freizeitlich weiterverfolgt
wird, ergibt dieser Ansatz Sinn.

#### Negativ-Beispiel

gewählte Klasse: `ChefkochRecipeFetcher`

vorher:

![Open-Closed negatives Beispiel UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/open-closed-neg-1.iuml)

nachher:

![Open-Closed negatives Beispiel UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/open-closed-neg-2.iuml)

Die `ChefkochRecipeFetcher`-Klasse ist ein Beispiel davon, wie das OCP verletzt werden kann. Diese
Klasse dient dazu, Daten aus einer Chefkoch-Website zu extrahieren. Dazu werden verschiedene
RegEx-Ausdrücke genutzt.

Das OCP ist hier nicht erfüllt, da die Klasse nicht offen für Erweiterungen ist, ohne gleichzeitig
geschlossen für Modifikationen zu sein. Sollte die Anforderung aufkommen, weitere Anbieter wie
KitchenStories.com zu integrieren oder gar die Chefkoch-Integration zu ersetzen, müssten einige
Code-Modifikationen durchgeführt werden. Dadurch ist das OCP verletzt.

Gelöst werden kann das durch ein Interface wie im zweiten UML ersichtlich. Ein
abstrakter `RecipeFetcher` kann als Interface für die Integration genutzt werden. Welche spezielle
Webseiten-Integration im Hintergrund benutzt wird, ist dabei irrelevant für die inneren Schichten
der Clean-Architecture. So wäre die Implementierung offen für weitere Erweiterungen unter Verwendung
des Interfaces. Gleichzeitig müssten keine aufwändigen Code-Modifikationen an den inneren Schichten
oder dem Interface vorgenommen werden, damit beide Module miteinander interagieren können. Dadurch
wäre das OCP erfüllt.

### 3.3. Analyse Dependency-Inversion-Principle (DIP)

Da zur Einhaltung der Dependency Rule der Clean Architecture-Methode oft das DIP genutzt wird,
können hier selbige Beispiele wie aus [Kapitel 2.2](#22-analyse-der-dependency-rule) genutzt werden.

#### Positiv-Beispiel

- gewählte Klasse: `DialogService`

![Dependency-Inversion-Principle positives Beispiel UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/dependency-inversion-pos.iuml)

Wie im obigen UML ersichtlich, wurde das DIP im `DialogService` eingehalten. Die
Klasse `DialogService` hängt von einer Abstraktion (dem Interface `RecipeRepository`) ab und nicht
von einer konkreten Implementierung (der Klasse `RecipeFileRepository`).

#### Negativ-Beispiel

- gewählte Klasse: `ChefkochRecipeFetcher`

![Dependency-Inversion-Principle negatives Beispiel UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/dependency-inversion-neg.iuml)

Das DIP wird in der Klasse `ChefkochRecipeFetcher` verletzt. Die Klasse `ChefkochRecipeFetcher`
verwendet direkt die Klasse `WebsiteFetcher`, um den Inhalt einer Webseite abzurufen. Hier wäre es
besser, ein Interface für das Abrufen von Webinhalten zu erstellen und dieses Interface als
Abstraktion zu verwenden.

## 4. Weitere Prinzipien

### 4.1. Analyse GRASP: Geringe Kopplung

#### Positiv-Beispiel

- gewählte Klasse: `DialogInputParser`

![Kopplung positives Beispiel UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/coupling-pos-1.iuml)

Diese Klasse ist für das Parsen der Benutzereingaben zuständig. Sie ist abhängig von `InputReader`
und `OutputService`, wobei es sich in beiden Fällen um Interfaces handelt. Damit hält die
Klasse `DialogInputParser` die Kopplung gering, da sie von Interfaces, statt konkreten
Implementierungen abhängt. Dadurch wird die Austauschbarkeit ermöglicht und die Testbarkeit der
Klasse verbessert.

#### Negativ-Beispiel

- gewählte Klasse: `ChefkochRecipeFetcher`

![Kopplung negatives Beispiel UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/coupling-neg.iuml)

Diese Klasse ist dafür zuständig, Rezeptinformationen von der Chefkoch-Website abzurufen und sie in eine Rezept-Instanz umzuwandeln. Sie ist von `WebsiteFetcher`, `Recipe`, `GroceryList` und `RecipeStep` abhängig. Durch die Abhängigkeit von `WebsiteFetcher` weist die Klasse `ChefkochRecipeFetcher` eine hohe Kopplung auf, da sie direkt die statische Methode `getWebsiteBody` aufruft. Dies könnte gelöst werden, indem man eine Schnittstelle für das Abrufen von Webseiten erstellt und diese Schnittstelle von der `ChefkochRecipeFetcher`-Klasse verwendet. Auf diese Weise könnten verschiedene Implementierungen zum Abrufen von Webseiten ausgetauscht werden, was die Kopplung verringert.

![Kopplung negatives Beispiel verbessert UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/coupling-neg-better.iuml)

#### Positiv-Beispiel 2
Da eine Klasse gefordert wurde, die nicht bereits in einem vorigen Kapitel behandelt wurde und dies beim vorherigen Negativ-Beispiel mit `ChefkochRecipeFetcher` nicht der Fall ist und kein weiteres Negativ-Beispiel existiert, wird ein weiteres Positiv-Beispiel aufgeführt.

- gewählte Klasse: `RecipeRepository`

![Kopplung positives Beispiel 2 UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/coupling-pos-2.iuml)

Die `RecipeRepository` Klasse ist ein Interface, das die Methoden für den Zugriff auf Rezept-Entities definiert. Sie ist lediglich von `Recipe` abhängig. Daher ist es ein weiteres Beispiel für geringe Kopplung. Es definiert lediglich die benötigten Methoden für den Zugriff auf Rezept-Entities, ohne sich auf eine spezifische Implementierung festzulegen. Die Implementierung von `RecipeRepository` (z.B. `RecipeFileRepository`) kann dann von der Anwendungsentwicklung abhängig gemacht werden, ohne die gesamte Anwendung zu beeinflussen. 

### 4.2. Analyse GRASP: Hohe Kohäsion

- gewählte Klasse: `Recipe`

![Kohäsion Beispiel UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/cohesion.iuml)

Die Klasse `Recipe` repräsentiert ein Rezept und besteht aus einem Namen, einer
Einkaufsliste (`GroceryList`) und einer Liste von Rezeptschritten (`RecipeStep`).
Sie weist hohe Kohäsion auf, da sie eng verwandte Attribute und Methoden enthält, die speziell für
die Repräsentation eines Rezepts erforderlich sind. Alle Attribute sind eng miteinander verbunden
und arbeiten zusammen, um ein konsistentes Rezeptmodell bereitzustellen. Die Klasse hat keine
zusätzlichen Verantwortlichkeiten, die nicht direkt mit der Darstellung eines Rezepts
zusammenhängen.

### 4.3. Don’t Repeat Yourself (DRY)

Commit-SHA:
d89dcb3 ([Link](https://github.com/ncryptedV1/AutoChef/commit/d89dcb38a0e45759dd3e689593870d1e9ed0da96))

vorher:

```java
  public MealPlan(List<Meal> meals,LocalDate start,LocalDate end){
    int days = start.until(end).getDays();
    if(meals.size()!=days){
      throw new IllegalArgumentException(
        "Mahlzeiten-Plan spannt "+days+" Tage, es wurden allerdings nur "+meals.size()
        +" Mahlzeiten übergeben");
    }

    this.meals=meals;
    this.start=start;
    this.end=end;
  }

// ...

  public int getDays(){
    return start.until(getEnd()).getDays();
  }
```

nachher:

```java
  public MealPlan(List<Meal> meals,LocalDate start,LocalDate end){
    this.start = start;
    this.end = end;

    int days=getDays();
    if(meals.size()!=days){
      throw new IllegalArgumentException(
        "Mahlzeiten-Plan spannt "+days+" Tage, es wurden allerdings nur "+meals.size()
        +" Mahlzeiten übergeben");
    }

    this.meals=meals;
  }

// ...

  public int getDays(){
    return start.until(getEnd()).getDays();
  }
```

Die oben gezeigte Änderung ist ein kleines Beispiel zur Reduktion von Code-Duplikationen. Die
Methode `getDays` war bereits vor dem Commit vorhanden. Sie dient dazu, die Anzahl an Tagen
zwischen `start` und `end` zu berechnen. Vor dem Commit, wurde jedoch dieselbe Logik im Konstruktor
in der 2. Zeile verwendet:

```java
int days = start.until(end).getDays();
```

Der Commit sorgte dafür, dass dieser Code durch einen Aufruf der `getDays` Methode ersetzt wurde.
Das hat zur Folge, dass die Logik der Berechnung der Anzahl der Tage nur an einem Punkt im Code
genutzt wird: in der `getDays` Methode. Dadurch können Fehler vermieden werden, die durch unachtsame
Änderungen an einer der beiden Code-Stellen aufgetreten wären.

## 5. Unit Tests

### 5.1. 10 Unit Tests

| Unit Test                                         | Beschreibung                                                                                                                                |
|---------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------|
| _TestUnit#testHashCodeTrue_                       | testet, ob die `hashCode` Funktion der `Unit` Klasse, für zwei Objekte, den selben Hashcode korrekt zurückgibt                              |
| _TestUnit#testEqualsResSame_                      | testet, ob die `equals` Methode der `Unit` Klasse zwei Instanzen mit dem selben `value` als korrekt gleich vergleicht                       |
| _TestQuantity#testConstructorException_           | testet, ob der Konstruktur der `Quantity` Klasse fehlschlägt, sobald negative (invalide) Werte übergeben werden                             |
| _TestQuantity#testMultiply_                       | testet, ob die `multiply` Methode der `Quantity` Klasse den Wert korrekt multipliziert                                                      |
| _TestGroceryItem#testEqualsResSelf_               | testet, ob die `equals` Methode der `GroceryItem` Klasse zwei Objekte mit dem selben `value` als gleich ansieht                             |
| _TestWebsiteFetcher#testGetWebsiteBodyInvalidUrl_ | testet, ob die `getWebsiteBodyInvalidUrl` Methode der `WebsiteFetcher` Klasse eine Exception wirft bei einer invaliden URL                  |
| _TestChefkochRecipeFetcher#testGetRecipe_         | testet, ob die `getRecipe` Methode der `ChefkochRecipeFetcher` Klasse die richtigen Inhalte für ein Chefkoch-Rezept aus dem Internet liefert |
| _TestRecipe#testConstructorHappyPath_             | testet, ob der Konstrutor der `Recipe` Klasse nicht `null` zurückgibt                                                                       |
| _TestMeal#testGetGroceryList_                     | testet, ob die `getGroceryList` Methode der `Meal` Klasse eine korrekt aggregierte Zutatenliste zurückgibt                                  |
| _TestGroceryList#testAddItem_                     | testet, ob die `addItem` Methode der `GroceryList` Klasse korrekt ein `GroceryItem` zur GroceryList hinzufügt                               |

### 5.2. ATRIP: Automatic

Die Tests wurden mittels Testautomatisierung realisiert. Dabei wurde JUnit 5 verwendet, um
automatisierte Tests zu schreiben. Über die IDE IntelliJ IDEA können die Test simpel über einen
Knopfdruck ausgeführt werden. Im Anschluss laufen alle Tests automatisch. Das Ergebnis der
Testautomatisierung zeigt, ob ein individueller Test erfolgreich (bestanden) oder nicht
erfolgreich (fehlgeschlagen) war.

### 5.3. ATRIP: Thorough

#### positives Beispiel

Test-Klasse: `TestIngredient`

```java
  @Test
  void testEqualsResSelf(){
      // arrange
      Ingredient ingredient1=mock(Ingredient.class);

    // act
    boolean res=ingredient1.equals(ingredient1);

    // assert
    assertTrue(res);
  }

  @Test
  void testEqualsResSame(){
      // arrange
      String value="banana";
      Ingredient ingredient1=new Ingredient(value);
      Ingredient ingredient2=new Ingredient(value);

      // act
      boolean res=ingredient1.equals(ingredient2);

      // assert
      assertTrue(res);
    }

  @Test
  void testEqualsDifferent(){
      // arrange
      Ingredient ingredient1=new Ingredient("banana");
      Ingredient ingredient2=new Ingredient("nutella");

      // act
      boolean res=ingredient1.equals(ingredient2);

      // assert
      assertFalse(res);
    }

  @Test
  void testEqualsNull(){
      // arrange
      Ingredient ingredient1=mock(Ingredient.class);

    // act
    boolean res=ingredient1.equals(null);

    // assert
    assertFalse(res);
  }

  @Test
  void testHashCodeTrue(){
      // arrange
      String value="banana";
      Ingredient ingredient1=new Ingredient(value);
      Ingredient ingredient2=new Ingredient(value);

      // act
      int code1=ingredient1.hashCode();
      int code2=ingredient2.hashCode();

      // assert
      assertEquals(code1,code2);
    }

  @Test
  void testHashCodeFalse(){
      // arrange
      Ingredient ingredient1=new Ingredient("banana");
      Ingredient ingredient2=new Ingredient("nutella");

      // act
      int code1=ingredient1.hashCode();
      int code2=ingredient2.hashCode();

      // assert
      assertNotEquals(code1,code2);
    }
```

Diese Testklasse mit den dargestellten Methoden ist ein Positivbeispiel für "thorough testing". All
diese Testmethoden testen verschiedene Zweige der selbstimplementieren `equals` Methode der
Klasse `Ingredient`. Sie testen Vergeliche zwischen

- einer `Ingredient`-Instanz mit sich selbst
- zwei gleichen `Ingredient`-Instanzen
- zwei verschiedenen `Ingredient`-Instanzen
- `null` und einer `Ingredient`-Instanz

Damit sind alle relevanten Pfade der `equals` Methode abgedeckt. Außerdem wird auch die Umgebung
getestet. Da die `equals` Methode, auf die `hashcode` Methode zugreift, können Fehler in
der `equals` Methode auf Fehler in der dortigen zurückgeführt werden. Demnentsprechend müssen auch
alle relevanten Pfade der `hashcode` Methode getestet werden, was hier gemacht wird.

#### negatives Beispiel

zu testende Methode: `DialogService#startMealPlanGeneration`

```java
  private void startMealPlanGeneration(){
    currentState = DialogState.MEAL_PLAN_GENERATION;

    outputService.rawOut("Wir generieren jetzt zusammen einen Mahlzeiten-Plan. :D");
    LocalDate startDate = inputParser.getDate(null, null,
      "Wann soll der Plan beginnen? (DD.MM.YYYY)");
    LocalDate endDate = inputParser.getDate(startDate, null,
      "Bis wann soll der Plan gehen (exklusiv)? (DD.MM.YYYY)");
    int people = inputParser.getInteger(1, 99,
      "Für wie viele Leute soll der Plan generiert werden?");
    int days = startDate.until(endDate).getDays();
    outputService.rawOut("Ok, ich generiere einen Plan für " + days + " Tage...");

    MealPlanBuilder mealPlanBuilder = new MealPlanBuilder();
    mealPlanBuilder.setStartDate(startDate).setEndDate(endDate);

    List<Recipe> recipes = recipeRepository.getRecipes();
    for (int i = 0; i < days; i++) {
      mealPlanBuilder.addMeal(startDate.plusDays(i),
      new Meal(recipes.get(random.nextInt(recipes.size())), people));
    }

    startPostMealPlanGeneration(mealPlanBuilder.build(), recipes);
  }
```

Die hier gezeigt Methode `DialogService#startMealPlanGeneration` wurde nicht getestet, obwohl es
möglich wäre dies zu tun. Damit stellt sie ein Negativbeispiel für die "thorough testing" Eigenschaft dar. Der gezeigt Code startet die Generierung eines Essensplans. Dazu werden verschiedene Nutzereingaben abgefordert, z.B. Start- und Enddatum, Anzahl der Personen. Im Anschluss werden die Rezepte geladen und mit einer zufälligen Teilmenge wird der Essensplan erstellt. Der Essensplan und die Liste an Rezepten werden im Anschluss an eine weitere Methode übergegeben.

Da die Tests für diese Methode vollständig fehlen, werden dementsprechend auch alle Pfade nicht
getestet. Deshalb kann nicht herausgefunden werden, wo sich logische Fehler befinden.

### 5.4. ATRIP: Professional

#### positives Beispiel

Test-Methode: `TestWebsiteFetcher#testGetWebsiteBodyInvalidUrl`

```java
  @Test
public void testGetWebsiteBodyInvalidUrl(){
    // Given
    String invalidUrl="any string";

    // When and Then
    assertThrows(IllegalArgumentException.class,()->WebsiteFetcher.getWebsiteBody(invalidUrl));
    }
```

Diese Testmethode testet die `getWebsiteBody` Methode der `WebsiteFetcher` Klasse. Hierbei wird ein
String, der eine invalide URL darstellt, in die zu testende Funktion übergeben. Anschließend wird
die `getWebsiteBody` Methode aufgerufen und überprüft, ob die richtige Exception geworfen wird.

Diese Testmethode ist ein Positivbeispiel für professionelle Testmethoden aus mehreren Gründen:

1. Der Name der Testmethode beschreibt gut, was genau getestet wird. In diesem Fall
   die `getWebsiteBody` Methode bei Eingabe einer invaliden URL.
2. Die zugehörige Klasse wurde nur zu Testzwecken angelegt.
3. Im Gegensatz zu Getter- oder Setter-Methoden existiert Logik, die getestet werden sollte. Ein
   Test ist dementsprechend notwendig.

#### negatives Beispiel

Test-Methode: `TestUnit#getValue`

```java
  @Test
  void testGetValue(){
    // arrange
    String expected="piece";
    Unit unit=new Unit(expected);

    // act
    String res=unit.getValue();

    // assert
    assertEquals(expected,res);
  }
```

Diese Testmethode testet die `getValue` Getter-Methode der `Unit` Klasse. Dabei wird ein `Unit`
ValueObject angelegt mit einem initialen Wert. Das Ergebnis der `getValue` wird verglichen mit dem
initialen Wert. Beide Werten sollten gleich sein.

Diese Klasse ist ein Negativbeispiel, da sie einen unnötigen Test darstellt. Getter-Methoden sollten
nicht getestet werden. Des Weiteren enthält diese Methode keine komplexe Logik, die ein Testen
erfordern würde. Es handelt sich hier um einen Test, "der nur wegen des Tests geschrieben wurde".
Außerdem ist der Dokumentationswert der Methode nicht vorhanden.

### 5.5. Code Coverage

Die folgende Tabelle zeigt die summierten Werte der verschiedenen Arten von Testabdeckung des
Projektes. Eine aufgeschlüsselte Version der Testabdeckung ist im folgenden Bild zu sehen. Die
Prozentangaben des Bildes folgen derselben Reihenfolge wie in der Tabelle aufgelistet.

| Art             | %  |
|-----------------|----|
| Class-Coverage  | 70 |
| Method-Coverage | 66 |
| Line-Coverage   | 51 |

![test coverage](./docs/res/cov.png)

Im Allgemeinen bestand das Ziel, die Testabdeckung so hoch wie möglich zu halten. Deshalb wurden
weitesgehend alle Klassen automatisiert getestet, jedoch nicht alle. Das hat vor allem den Grund,
dass die automatisierte Testung für einige Klassen unserer Ansicht nicht sinnhaft war. Beispiele
hierfür sind:

- `domain.util.Formats`: Diese Klasse dient lediglich als Format-Klasse ohne weitere Logik.
- `domain.util.io.ConsoleOutputService`: Da es sich hier vor allem um die Formattierung von
  Konsolenausgaben handelt, wurden hier die Tests ebenso vernachlässigt.

Unabhängig dessen begründet sich die Code-Coverage wie folgt:

- Class-Coverage: die Mehrheit der Klassen weist Tests auf, weshalb der Wert hier mit 70%
  vergleichsweise hoch liegt.
- Method-Coverage: Aus genannten Gründen wurden eine Reihe von Methoden nicht getestet, daher liegt
  die Method-Coverage unter der Class-Coverage.
- Line-Coverage: Die Line-Coverage ist durch die Method-Coverage bedingt und ist deshalb vor allem
  aus demselbigen Grund niedriger.

### 5.6. Fakes und Mocks

In diesem Projekt wurden vor allem Mock-Objekte eingesetzt. Sie wurden genutzt, um benötigte Nebenklassen zu mocken. Jedoch wurden auch einige Fake Objekte an Stellen genutzt, an denen die Funktionalitäten von Mock-Objekten nicht ausreichten.

#### Fake-Objekt 1: `OutputServiceFake`

![Fakes und Mocks Beispiel 1 UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/fake-mock-1.iuml)

Die `OutputServiceFake`-Klasse ist ein Fake-Objekt, das die Ausgabe an die Konsole imitiert. Dieses Projekt nutzt Ein- und Ausgabefunktionen für die Interaktion mit dem Benutzer. Da beide Funktionalitäten jedoch automatisierten Unit-Tests im Wege stehen, musste ein Weg gefunden werden sie zu umgehen. Dazu wurden Fake-Objekte eingeführt. 

Möglich ist das durch die Nutzung eines `OutputService`-Interfaces, das die Konsolenausgabe abstrahiert. So ist es möglich, dass Code der inneren Schichten abstrakt mit Code der äußeren Schichten im Sinne der Clean-Architecture interagieren kann. Das `OutputService` bietet einige Funktionalitäten, um Konsolenausgaben auf verschiedenen Art und Weisen auszugeben, wie im UML-Diagram ersichtlich ist. Da für die automatisierten Tests zunächst keine Konsolenausgaben benötigt werden, wurden die eigentlichen Implementierungen in der `OutputServiceFake`-Klasse leer gelassen. 

#### Fake-Objekt 1: `ConsoleInputReaderFake`

![Fakes und Mocks Beispiel 2 UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/fake-mock-2.iuml)

Ein zweiter Aspekt, der ein Fake-Objekt nutzt, ist die Eingaben von Daten durch den Nutzer. Speziell ist damit die Funktionalität der `ConsoleInputReader`-Klasse gemeint. Diese Klasse wird lediglich als Teil der `DialogInputParser`-Klasse genutzt. Dort werden Daten des Benutzers über die Konsole durch die `ConsoleInputReader`-Klasse eingelesen. Im zweiten Schritt analysiert und validiert die `DialogInputParser`-Klasse die Eingabe um sie weiter verwenden zu können. 

Die Benutzung der Konsole ist jedoch hinderlich, wenn automatisierte Tests genutzt werden. Um die Funktionalitäten der `DialogInputParser`-Klasse testen zu können, muss also ein Fake für die `ConsoleInputReader`-Klasse genutzt werden. Die `ConsoleInputReaderFake`-Klasse hat genau diese Verantwortung. Genauso wie die `ConsoleInputReader`-Klasse, greift der Fake auf ein Interface namens `InputReader` zu. Dieses Interface bietet als einzige Funktionlität eine `readLine`-Methode, die Inhalte des Benutzers einliest. 

Der Fake soll auf eine Art und Weise funktionieren, dass er Daten, die hineingegeben werden genauso wieder zurückgegeben werden. Da die `readLine`-Methode laut Interface aber keine Parameter erwartet, wurde ein zusätzlicher Konstruktor hinzugefügt. Wie im UML ersichtlich, nimmt der Konstruktor einen String auf, der in der `content`-Variable gespeichert wird. Intern wird der Inhalt dieser Variable lediglich zurückgegeben. So ist es möglich einen Fake zu verwenden und gleichzeitig effektiv zu testen.

## 6. Domain Driven Design

### 6.1. Ubiquitous Language

| Bezeichnung  | Bedeutung                     | Begründung                                                                                                                          |
|--------------|-------------------------------|-------------------------------------------------------------------------------------------------------------------------------------|
| Ingredient   | Zutat                         | Jedes Gericht besteht aus verschiedenen Lebensmitteln sogenannten Zutaten.                                                          |
| Grocery item | Element der Lebensmittelliste | Ein Gericht braucht Zutaten in bestimmten Mengen. Lebensmittel auf der Lebensmittelliste sind in Menge und Einheit daran angepasst. |
| Grocery list | Lebensmittelliste             | Für Gerichte werden eine Menge von Lebensmittel benötigt. Die Lebensmittelliste fasst all diese zusammen.                           |
| Meal plan    | Essensplan                    | Ein Essensplan beschreibt eine Sammlung von Gerichten für einen bestimmten Zeitraum.                                                |

### 6.2. Entities

zugehörige Klasse(n): `Recipe`

![Entity Beispiel UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/entity.iuml)

Die `Recipe` Entity beschreibt ein semantisches Rezept. Wie das UML-Diagram zeigt, besteht ein
Rezept aus einem Name, einer Liste von Zutaten (GroceryList) und einer Liste von Schritten zur
Zubereitung (RecipeStep). Ein Rezept wird eineindeutig über eine ID identifiziert. In diesem Fall
besteht die ID aus dem Namen in Kleinschrift. Haben also zwei Rezepte den selben Namen, werden sie
als gleich angesehen. Weiterhin hat die `Recipe` Klasse mehrere Getter-Methoden für die einzelnen
Attribute und die ID als auch einen Konstruktor.

Bei der Erstellung einer `Recipe`-Instanz mittels des Konstruktors wird die Richtigkeit der
Attribute überprüft:

- Der Name muss mindestens ein Zeichen abgesehen von White-Space beinhalten.
- Die `RecipeStep`-Instanzen der Liste müssen in der richtigen Reihenfolge und konsektutiv
  vollständig sein, d.h. es dürfen keine Schritten zwischendrin fehlen.

Der Einsatz dieser Entity begründet sich dadurch, dass es notwendig war, ein Rezept abbilden zu
können. Rezepte werden gespeichert und haben somit einen Lebenszyklus. Das erzwingt laut Domain
Driven Design Richtlinien die Erstellung einer Entity.

### 6.3. Value Objects

zugehörige Klasse(n): `Ingredient`

![Entity Beispiel UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/value-object.iuml)

Das `Ingredient` Value Object beschreibt eine Zutat für ein Gericht. Es besitzt als einziges
Attribute einen Namen, der es definiert. Neben dem Namen besitzt es zwei Getter-Methoden für den
Namen (`getValue`) und für die ID (`getID`). Letztere, ist dabei jedoch nicht zur eindeutigen
Identifizierung im Sinne einer Entity anzusehen, sondern wird lediglich zum einfacheren Vergleich
zweier `Ingredient`-Instanzen verwendet. Die ID setzt sich aus dem Namen in Kleinschrift zusammen.
Zusätzlich existiert ein Konstruktor zur Erzeugung einer Ingredient-Instanz, bei der die Richtigkeit
des Namens überprüft wird. Ähnlich wie bei der `Recipe`-Klasse, muss ein Namen mindestens ein
Zeichen enthalten, das keinem White-Space Zeichen entspricht.

Ein `Ingredient` hat keinen Lebenszyklus und auch keine relevante Logik implementiert. Es dient
lediglich zur Repräsentation von Informationen. Aus diesem Grund ist es auch nicht möglich die
Informationen einer `Ingredient`-Instanz anzupassen - sie sind konstant. All diese Punkte begründen,
warum sich hier für ein Value Object anstelle einer Entity oder Ähnlichem entschieden wurde.

### 6.4. Repositories

zugehörige Klasse(n): `RecipeFileRepository`

![Repository Beispiel UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/repository.iuml)

Repositories dienen als Vermittler zwischen Datenmodell und Domänenlogik. Sie werden genutzt, um
Daten zu speichern oder sie abzurufen aus ebendiesem Speicher. In diesem Projekt wurde ein
Repository `RecipeFileRepository` genutzt, um die Persistenz von Rezepten zu verwalten.
Das `RecipeFileRepository` besitzt als einziges Attribut eine Instanz der `File`-Klasse
namens `recipesFolder`, die angibt, wo im Dateisystem Rezepte gespeichert werden sollten.
Zusätzlich existieren Methoden zur Persistenzverwaltung, die von dem Interface `RecipeRepository`
implementiert werden:
- `saveRecipe`: speichert ein gegebenes Rezept in einer Datei im Dateiort `recipesFolder` ab
- `deleteRecipe`: löscht ein gegebenes Rezept im Dateiort `recipesFolder`
- `getRecipe`: liest ein anhand der ID definiertes Rezept im Dateiort `recipesFolder` ein
- `getRecipes`: liest alle vorhandenen Rezepte im Dateiort `recipesFolder` ein

Damit sind die relevanten Methoden der Persistenzverwaltung abgedeckt. Bei der Erstellung
einer `RecipeFileRepository`-Instanz mittels des Konstruktors wird ebenso geprüft, ob ein Ordner im
Pfad `recipesFolder` angelegt werden kann. Sollte das nicht der Fall sein, tritt ein Fehler auf und
es kann keine Instanz angelegt werden.

Um die Persistenzverwaltung gründlich und sauber von der Domänenlogik zu trennen, wurde dieses
Repository eingesetzt. Mit Nutzung des Interfaces, kann sichergestellt werden, dass beide Elemente
getrennt bleiben. Zusätzlich ermöglicht der Einsatz eines Repositories, Veränderungen an der
Persistenzverwaltung vorzunehmen, ohne auf die Domänenlogik eingreifen zu müsssen.

### 6.5. Aggregates

zugehörige Klasse(n): `Meal`

![Aggregate Beispiel UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/aggregate.iuml)

In diesem Projekt wurde die `Meal`-Klasse als Aggregate ausgewählt. Die `Meal`-Klasse fasst logisches
Verhalten verschiedener Elemente zusammen. Es definiert sich durch ein Rezept `recipe` und einem
Integer `adjustedNumberOfPeople`, das darstellt, auf wie viele Personen die Zutatenmenge des
Rezeptes angepasst werden soll. Der Konstruktor enthält keine weitere Logik zur Überprüfung der
Attribute. Des Weiteren existieren folgende Methoden:

- `getRecipe`: eine Getter-Methode für `recipe`
- `setRecipe`: eine Setter-Methode für `recipe`
- `getAdjustedNumberOfPeople`: eine Getter-Methode für `adjustedNumberOfPeople`
- `getGroceryList`: eine Getter-Methode, die die Zutaten des Rezeptes zurückgibt. Dabei werden die
  Mengen der Zutaten auf `adjustedNumberOfPeople` angepasst.

Damit fasst das `Meal`-Aggregate Logik eines Gerichtes zusammen. Es hält jedoch keine eigene
Identität, da dies bereits durch `Recipe` erfolgt ist. Es dient lediglich als Wrapper für ein Rezept
um jenes zu repräsentieren. In der Theorie wäre es möglich, der `Meal`-Klasse eine eigenständige
Identität und Lebenszyklus zu verleihen und es damit zu einer Entity zu transferieren. Das wäre vor
allem hilfreich, wenn Logik zur Persistierung von Gerichten implementiert werden sollte. Da das aber
nicht im Umfang der Anwendung inbegriffen ist, ist ein Transfer zu einer Entity nicht notwendig.

## 7. Refactoring

### 7.1. Code Smells

#### Code Smell 1

Code Smell: Duplicate Code
([Commit](https://github.com/ncryptedV1/AutoChef/commit/d89dcb38a0e45759dd3e689593870d1e9ed0da96))

vorher:

```java
  public MealPlan(List<Meal> meals,LocalDate start,LocalDate end){
    int days=start.until(end).getDays();
    if(meals.size()!=days){
    throw new IllegalArgumentException(
    "Mahlzeiten-Plan spannt "+days+" Tage, es wurden allerdings nur "+meals.size()
    +" Mahlzeiten übergeben");
    }

    this.meals=meals;
    this.start=start;
    this.end=end;
    }

// ...

public int getDays(){
    return start.until(getEnd()).getDays();
    }
```

nachher:

```java
  public MealPlan(List<Meal> meals,LocalDate start,LocalDate end){
    this.start=start;
    this.end=end;

    int days=getDays();
    if(meals.size()!=days){
    throw new IllegalArgumentException(
    "Mahlzeiten-Plan spannt "+days+" Tage, es wurden allerdings nur "+meals.size()
    +" Mahlzeiten übergeben");
    }

    this.meals=meals;
    }

// ...

public int getDays(){
    return start.until(getEnd()).getDays();
    }
```

Dieser Code Smell umfasst eine Code Duplication in der `MealPlan`-Klasse. Relevant ist hierbei vor
allem Zeile 2. Hier wird die folgende Berechnung ausgeführt:

```
int days = start.until(end).getDays();
```

`days` gibt dabei die Anzahl an Tagen zwischen `start` udn `end` an. Was dabei aber auffällt, ist,
dass diese Logik bereits zu anderen Zwecken in der `getDays`-Methode vorhanden ist. Diese Methode
wird verwendet, um auch von außen Zugriff auf die Anzahl der Tage zu haben. Damit kann hier eine
kleine Dopplung vorgefunden werden. Diese wurde nun, wie im zweiten Code-Beispiel ersichtlich,
behoben. Dabei wurde die Zuweisung der Variable `this.start` und `this.end` nach oebn verschoben,
sodass dann die Methode `getDays` aufgerufen werden kann. Dieser Methodenaufruf ermöglicht es jetzt,
die Logik der Berechnung der Anzahl der Tage zu kapseln und somit die Wartbarkeit zu erhöhen.

#### Code Smell 2

Code Smell 2: Large Method
([Commit](https://github.com/ncryptedV1/AutoChef/commit/990e2b31be1fb10314691630584319d48b3d0cd8#diff-fdae48ae8bf101ffc36c43d7bbad7c1da1bef6ba56429655006dac33210fe387))

vorher:

```java
  public static void main(String[]args){
    logger.info("Starting...");

    // setup groceries
    GroceryItem item1=new GroceryItem(new Ingredient("Banane"),new Quantity(1),Unit.GRAM);
    GroceryItem item2=
    new GroceryItem(new Ingredient("Pineapple"),new Quantity(0.2),Unit.KILOGRAM);
    GroceryItem item3=
    new GroceryItem(new Ingredient("Orange juice"),new Quantity(0.1),Unit.LITER);
    GroceryItem item4=new GroceryItem(new Ingredient("Apple"),new Quantity(1),Unit.PIECE);
    GroceryItem item5=
    new GroceryItem(new Ingredient("Nutella"),new Quantity(2),Unit.TABLESPOON);

    // setup recipe steps
    RecipeStep recipeStep1=
    new RecipeStep(1,"Cut some banana, apple and pineapple as the basis for this salad.",
    item1,item2,item3);
    RecipeStep recipeStep2=new RecipeStep(2,"Add orange juice to make it more juicy.",item4);
    RecipeStep recipeStep3=
    new RecipeStep(3,"Add a bit of Nutella for making it look beautiful.",item5);

    // setup recipe for
    Recipe recipe1=new Recipe("Sugar-free fruit salad",recipeStep1,recipeStep2,recipeStep3);

    // setup meal
    Meal meal1=new Meal(recipe1,2);
    // setup meal plan
    List<Meal> mealList=Arrays.asList(meal1);
    LocalDate startDate=LocalDate.of(2023,2,20);
    LocalDate endDate=LocalDate.of(2023,2,26);
    MealPlan mealPlan=new MealPlan(mealList,startDate,endDate);

    logger.info(mealPlan.toString());
    }
```

nachher:

```java
  public static void main(String[]args){
    logger.info("Starting...");

    // generate mock data
    List<GroceryItem> groceryItems=MockService.generateGroceryItems();
    List<RecipeStep> recipeSteps=MockService.generateRecipeSteps(groceryItems);
    Recipe recipe=MockService.generateRecipe(recipeSteps);
    Meal meal=MockService.generateMeal(recipe);
    List<Meal> meals=Arrays.asList(meal);
    MealPlan mealPlan=MockService.generateMealPlan(meals);

    logger.info(mealPlan.toString());
    }
```

```java
public class MockService {

  private static final Random random = new Random();

  public static List<GroceryItem> generateGroceryItems() {
    GroceryItem item1 = new GroceryItem(new Ingredient("Banane"), new Quantity(1), Unit.GRAM);
    GroceryItem item2 = new GroceryItem(new Ingredient("Pineapple"), new Quantity(0.2),
        Unit.KILOGRAM);
    GroceryItem item3 = new GroceryItem(new Ingredient("Orange juice"), new Quantity(0.1),
        Unit.LITER);
    GroceryItem item4 = new GroceryItem(new Ingredient("Apple"), new Quantity(1), Unit.PIECE);
    GroceryItem item5 = new GroceryItem(new Ingredient("Nutella"), new Quantity(2),
        Unit.TABLESPOON);
    return Arrays.asList(item1, item2, item3, item4, item5);
  }

  public static List<RecipeStep> generateRecipeSteps(List<GroceryItem> groceryItems) {
    RecipeStep recipeStep1 = new RecipeStep(1,
        "Cut some banana, apple and pineapple as the basis for this salad.",
        getSample(groceryItems));
    RecipeStep recipeStep2 = new RecipeStep(2, "Add orange juice to make it more juicy.",
        getSample(groceryItems));
    RecipeStep recipeStep3 = new RecipeStep(3, "Add a bit of Nutella for making it look beautiful.",
        getSample(groceryItems));
    return Arrays.asList(recipeStep1, recipeStep2, recipeStep3);
  }

  public static Recipe generateRecipe(List<RecipeStep> recipeSteps) {
    return new Recipe("Sugar-free fruit salad", recipeSteps);
  }

  public static Meal generateMeal(Recipe recipe) {
    return new Meal(recipe, 2);
  }

  public static MealPlan generateMealPlan(List<Meal> meals) {
    LocalDate startDate = LocalDate.of(2023, 2, 20);
    LocalDate endDate = LocalDate.of(2023, 2, 26);
    return new MealPlan(meals, startDate, endDate);
  }

  private static <T> List<T> getSample(List<T> list) {
    int sampleSize = random.nextInt(1, list.size());
    List<T> result = new ArrayList<>(list);
    result = result.subList(0, sampleSize);
    return result;
  }
}
```

Der zweite Code Smell befasst sich mit der Auslagerung der `MockService`-Klasse, die im aktuellen
Stand nicht mehr vorhanden ist. In diesem Fall ist der Code Smell eine _Large Method_: die `main`
-Methode. Sie umfasst die Erstellung einiger Objekte, damit die Anwendung mit Testdaten getestet
werden konnte. Da es sich hier um vergleichsweise viele Objekte handelt, ist die Methode groß.

Um das zu beheben, wurde die vorhandene Logik extrahiert in eine `MockService`-Klasse. Diese Klasse
ist nun dafür verantwortlich, Testobjekte und -daten zu generieren und über Methodenaufrufe
zurückzugeben. Dabei sind mehrere Methoden im Mock-Service entstanden, was die Komplexität des
Code-Smells zeigt.

### 7.2. 2 Refactorings

[//]: # (Refactoring: Extract Method)

[//]: # ([Commit]&#40;https://github.com/ncryptedV1/AutoChef/commit/8a66d9a6405c9ca4cf710490ae06eb810ea87978#diff-a914343e6af07030cd7b3b51d56fc5e0f541d6bd350ee0ef11324a9bc5aae66f&#41;)

#### Refactoring 1

Refactoring 2: Extract Class (ConsoleOutputService)
([Commit](https://github.com/ncryptedV1/AutoChef/commit/d2251bcb4cc4053a1de24e20213a46034d3e0dbf))

vorher:

![Refactoring Beispiel 1 Pre UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/refactoring-1-pre.iuml)

nachher:

![Refactoring Beispiel 1 Post UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/refactoring-1-post.iuml)

Das in den UML-Diagrammen gezeigte Refactoring ist das _Extract Class_-Refactoring, das genutzt
wurde um die `ConsoleOutputService`-Klasse zu erschaffen. Der Commit und das erste UML-Diagramm
zeigen, dass die `AutoChef`-Klasse mit der `main`-Methode zunächst auch für die Konsolenausgabe
verantwortlich war. Um das aber sauber voneinander zu trennen, und die Komplexität der `main`
-Methode so einfach wie möglich zu halten, wurde die Logik der Consolenausgabe in eine separate
Klasse ausgelagert. Die neu geschaffene Klasse `ConsoleOutputService` umfasst neben der Logik, die
vorher in der `main`-Methode existierte, auch noch weitere Funktionalitäten. Das zeigt auch das
zweite UML-Diagramm. Zu sehen ist hier die `ConsoleOutputService`-Klasse mit ihren verschiedenen
Methoden zur Konsolenausgabe. Mit diesem Refactoring konnte eine saubere Trennung der
Verantwortlichkeiten, sowie eine kleinere `main`-Klasse erreicht werden.

#### Refactoring 2

Refactoring 2: Rename Method
([Commit](https://github.com/ncryptedV1/AutoChef/commit/19fb6fc9c8bdee1a0e1d31f31c431a38eaf68ae2#diff-a621a84bbbaeb91c8fc33118865eb8c87e0bf202d7dfe7b5bf8d20a786a51239))

vorher:

![Refactoring Beispiel 2 Pre UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/refactoring-2-pre.iuml)

nachher:

![Refactoring Beispiel 2 Post UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/refactoring-2-post.iuml)

Dieses Refactoring umfasst das Umbenennen einer Methode (_Rename Method_) um mehr Klarheit im
Quellcode zu schaffen. Es handelt sich dabei um die Methode `getIngredients`
respektive `getGroceryList` (nach dem Refactoring). Diese kleine Änderung im Code ist auch in den
UML-Diagrammen ersichtlich. Der Commit zeigt ebenso, dass die Größe der Änderung vergleichsweise
klein ist. Die `getIngredients`-Methode wurde dabei umbenannt in `getGroceryList`. Für den
Entwickler macht das vor allem deutlich, dass das Rückgabeergebnis eine `GroceryList` anstelle
einer `List<Ingredient>` ist. Das ist besonders wichtig, da es sich hier um verschiedene Objekte mit
unterschiedlichen Eigenschaften und Funktionalitäten handelt. So können vermeintliche Fehler in der
Benutzung der Methode durch mehr Klarheit vermieden werden, die sonst durch Unachtsamkeit
aufgetreten wären.

## 8. Entwurfsmuster

#### 8.1. Entwurfsmuster: Facade

- gewählte Klasse: `ConsoleOutputService`

![Entwurfstmuster Facade Beispiel UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/design-pattern-facade.iuml)

Die obige Klasse `ConsoleOutputService` stellt eine Facade für die `Logger`-Klasse dar, weil sie eine vereinfachte und einheitliche Schnittstelle für das Logging-System bereitstellt. Die Hauptziele einer Facade sind die Vereinfachung der Schnittstelle und die Entkopplung von Subsystemen.

In diesem Fall verbirgt die `ConsoleOutputService`-Klasse die Komplexität der `Logger`-Klasse und des LogManager-Systems, indem sie nur eine Reihe von statischen Methoden zur Verfügung stellt:

- `info(String msg)`: zum Loggen von Info-Nachrichten.
- `warning(String msg)`: zum Loggen von Warn-Nachrichten.
- `severe(String msg)`: zum Loggen von schwerwiegenden Fehlermeldungen.
- `rawOut(Object msg)`: zum direkten Ausgeben von Nachrichten auf der Standardausgabe (stdout).
- `rawErr(Object msg)`: zum direkten Ausgeben von Fehlermeldungen auf der Standardfehlerausgabe (stderr).

Die `ConsoleOutputService`-Klasse kapselt die Details der `Logger`-Konfiguration im statischen Block und -Initialisierung. Dies ermöglicht den Nutzern, die Logging-Funktionalität einfach zu verwenden, ohne sich um die zugrunde liegenden Details kümmern zu müssen.

#### 8.2. Entwurfsmuster: Builder

- gewählte Klasse: `MealPlanBuilder`

![Entwurfstmuster Builder Beispiel UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/design-pattern-builder.iuml)

Der `MealPlanBuilder` ist ein Beispiel für das Entwurfsmuster Builder, da er eine einfache Schnittstelle bietet, um komplexe Objekte schrittweise aufzubauen. Mithilfe von Methoden wie `setStartDate()`, `setEndDate()` und `addMeal()` kann der Verwender des `MealPlanBuilder` einen Mahlzeiten-Plan definieren, ohne dabei die genaue Implementierung des `MealPlan` kennen zu müssen.

Die `build()` Methode ist das Herzstück des Builders und erzeugt das fertige Objekt. Dabei wird die Konsistenz des Objekts sichergestellt und gegebenenfalls eine `IllegalStateException` geworfen, wenn zum Beispiel ein Start- oder Enddatum fehlt oder nicht alle Mahlzeiten definiert sind.

Der Builder ist ein nützliches Muster, wenn die Erstellung von Objekten viele Parameter erfordert oder komplex ist. Indem er den Verwendet von der Komplexität des Aufbaus des Objekts abschirmt, ermöglicht er eine klare und einfache API und stellt sicher, dass die erstellten Objekte korrekt initialisiert sind.
