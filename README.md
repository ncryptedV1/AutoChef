# Programmentwurf - AutoChef

Name: Schirmer, Oliver \
Martrikelnummer: XXX

Name: Müller, Luca \
Martrikelnummer: XXX

Abgabedatum: 28. Mai 2023

## Allgemeine Anmerkungen

- es darf nicht auf andere Kapitel als Leistungsnachweis verwiesen werden (z.B. in der Form “XY wurde schon in Kapitel 2 behandelt, daher hier keine Ausführung”)
- alles muss in UTF-8 codiert sein (Text und Code)
- sollten mündliche Aussagen den schriftlichen Aufgaben widersprechen, gelten die schriftlichen Aufgaben (ggf. an Anpassung der schriftlichen Aufgaben erinnern!)
- alles muss ins Repository (Code, Ausarbeitung und alles was damit zusammenhängt)
- die Beispiele sollten wenn möglich vom aktuellen Stand genommen werden
  - finden sich dort keine entsprechenden Beispiele, dürfen auch ältere Commits unter Verweis auf den Commit verwendet werden
  - Ausnahme: beim Kapitel “Refactoring” darf von vorne herein aus allen Ständen frei gewählt werden (mit Verweis auf den entsprechenden Commit)
- falls verlangte Negativ-Beispiele nicht vorhanden sind, müssen entsprechend mehr Positiv-Beispiele gebracht werden
  - Achtung: werden im Code entsprechende Negativ-Beispiele gefunden, gibt es keine Punkte für die zusätzlichen Positiv-Beispiele
  - Beispiel: “Nennen Sie jeweils eine Klasse, die das SRP einhält bzw. verletzt.”
  - -> Antwort: Es gibt keine Klasse, die SRP verletzt, daher hier 2 Klassen, die SRP einhalten: [Klasse 1], [Klasse 2]
  - -> Bewertung: falls im Code tatsächlich keine Klasse das SRP verletzt: volle Punktzahl ODER falls im Code mind. eine Klasse SRP verletzt: halbe Punktzahl
- verlangte Positiv-Beispiele müssen gebracht werden
- Code-Beispiel = Code in das Dokument kopieren

## 1. Einführung

### Übersicht über die Applikation

AutoChef ist eine Anwendung zur einfachen und effizienten Verwaltung und Erstellung von Essensplänen, sowie dazugehöriger Einkaufslisten. Zugrunde liegt dabei eine Datenbank an Rezepten. Diese ist beliebig erweiterbar durch eine [Chefkoch](chefkoch.de)-Integration. Diese ermöglicht es, einen Link zu einem Rezept auf Chefkoch an die Anwendung zu übergeben, woraufhin diese das entsprechende Rezept herunterlädt und persistiert. Diese Datenbank an Rezepten, sowie jeweilige Rezept-Details, können jederzeit eingesehen werden. Hauptfunktion ist jedoch die Generierung von Essensplänen. Dafür kann der Nutzer einen Zeitraum, sowie die Anzahl an Personen je Mahlzeit angeben und die Anwendung generiert anhand der Rezept-Datenbank, einen zufälligen Essensplan. Für diesen Essensplan wird ebenfalls eine Einkaufsliste generiert, die an die Anzahl an Personen angepasst ist. Mit diesen Funktionalitäten ist es einfach möglich, seine Woche kulinarisch zu planen und die Einkaufliste für den Wocheneinkauf zu erstellen.

_[Was macht die Applikation, Wie funktioniert sie? Welches Problem löst sie/welchen Zweck hat sie?]_

### Wie startet man die Applikation?

Bei AutoChef handelt es sich um eine CLI-Anwendung, geschrieben in Java 19. Zum Starten wird daher lediglich ein Desktop-Rechner mit **Java 19 aufwärts** benötigt. Die Anwendung kann dann über ein Konsolenfenster mit dem Befehl `java -jar AutoChef.jar` gestartet werden.

_[Wie startet man die Applikation? Welche Voraussetzungen werden benötigt? Schritt-für-Schritt-Anleitung]_

### Wie testet man die Applikation?

Nach dem Start der Anwendung wird der Nutzer durch einen intuitiven Dialog-Prozess begrüßt und geleitet. Der Nutzer interagiert dabei mit der Anwendung mittels des Konsolenfensters. Über dieses werden sowohl Informationen ausgegeben, als auch Eingaben vom Nutzer eingeholt. Der Dialog-Prozess ist so gestaltet, dass der Nutzer in der Regel mehrere nummerierte Optionen zur Auswahl hat und nur die Nummer der gewünschten Option eingeben und mit Enter absenden muss. Für den Import von Rezepten muss der Nutzer sich zuvor ein Rezept von Chefkoch aussuchen und im entsprechenden Dialog-Prozess-Schritt den dazugehörigen Link einfügen. Zu Beginn der Nutzung ist die Rezept-Datenbank noch leer, weshalb es sich anbietet anfangs ein paar Rezepte zu importieren. Erst danach können die Funktionen der Rezept-Anzeige und Essensplan-Generierung sinnvoll genutzt werden.

Hinweis: Zur Persistierung der Rezepte erstellt die Anwendung im aktuellen Arbeitsverzeichnis (des Konsolenfensters) einen `recipes`-Ordner. Falls der Nutzer keine Rechte hat im aktuellen Arbeitsverzeichnis Ordner & Dateien zu erstellen, sowie in diese zu schreiben, kann das Programm nicht ordnungsgemäß arbeiten und terminiert. Versuche in dem Fall die Anwendung mit erhöhten Berechtigungen zu starten oder in einem Arbeitsverzeichnis mit Schreibzugriff auszuführen.

_[Wie testet man die Applikation? Welche Voraussetzungen werden benötigt? Schritt-für-Schritt-Anleitung]_

## 2. Clean Architecture

### Was ist Clean Architecture?

Clean Architecture ist eine Architektur- und Designphilosophie, die darauf abzielt, komplexe Softwaresysteme in leicht verständliche, wartbare und erweiterbare Komponenten zu unterteilen. Es wurde von Robert C. Martin entwickelt und basiert auf den SOLID-Prinzipien.

Im Wesentlichen sieht Clean Architecture vor, dass eine Software in mehrere Schichten unterteilt wird, wobei jede Schicht eine klare Abhängigkeitshierarchie aufweist und nur von der nächstgelegenen Schicht abhängt. Die äußerste Schicht ist die Benutzerschnittstelle, die direkt mit dem Benutzer interagiert, gefolgt von einer oder mehreren Schichten mit Geschäftslogik, Datenzugriff und Infrastruktur.

Durch diese Aufteilung kann jede Schicht unabhängig von den anderen Schichten getestet und gewartet werden, was zu einer höheren Flexibilität und Skalierbarkeit des gesamten Systems führt. Darüber hinaus ist es einfacher, Änderungen an einem Teil des Systems vorzunehmen, ohne Auswirkungen auf den Rest des Systems zu haben.

Clean Architecture ermutigt auch zur Verwendung von Schnittstellen, um die Abhängigkeiten zwischen den Komponenten zu minimieren. Durch die Verwendung von Schnittstellen können verschiedene Implementierungen ausgetauscht werden, ohne dass dies Auswirkungen auf den Rest des Systems hat.

Zusammenfassend lässt sich sagen, dass Clean Architecture eine Methode ist, um große, komplexe Softwaresysteme in einfachere, leichter zu wartende Komponenten aufzuteilen, indem eine klare Abhängigkeitshierarchie zwischen den Komponenten eingeführt wird.

_[allgemeine Beschreibung der Clean Architecture in eigenen Worten]_

### Analyse der Dependency Rule

_[(1 Klasse, die die Dependency Rule einhält und eine Klasse, die die Dependency Rule verletzt); jeweils UML der Klasse und Analyse der Abhängigkeiten in beide Richtungen (d.h., von wem hängt die Klasse ab und wer hängt von der Klasse ab) in Bezug auf die Dependency Rule]_

#### Positiv-Beispiel: Dependency Rule

![Dependency Rule positives Beispiel UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/dependency-rule-pos.iuml)

#### Negativ-Beispiel: Dependency Rule

![Dependency Rule negatives Beispiel UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/dependency-rule-neg.iuml)

### Analyse der Schichten

_[jeweils 1 Klasse zu 2 unterschiedlichen Schichten der Clean-Architecture: jeweils UML der Klasse (ggf. auch zusammenspielenden Klassen), Beschreibung der Aufgabe, Einordnung mit Begründung in die Clean-Architecture]_

#### Schicht: [Name]

![Schicht 1 UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/layer-1.iuml)

#### Schicht: [Name]

![Schicht 2 UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/layer-2.iuml)

## 3. SOLID

### Analyse Single-Responsibility-Principle (SRP)

_[jeweils eine Klasse als positives und negatives Beispiel für SRP; jeweils UML der Klasse und Beschreibung der Aufgabe bzw. der Aufgaben und möglicher Lösungsweg des Negativ-Beispiels (inkl. UML)]_

#### Positiv-Beispiel

![Single Responsibility positives Beispiel UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/single-responsibility-pos.iuml)

#### Negativ-Beispiel

![Single Responsibility negatives Beispiel UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/single-responsibility-neg.iuml)

### Analyse Open-Closed-Principle (OCP)

_[jeweils eine Klasse als positives und negatives Beispiel für OCP; jeweils UML der Klasse und Analyse mit Begründung, warum das OCP erfüllt/nicht erfüllt wurde – falls erfüllt: warum hier sinnvoll/welches Problem gab es? Falls nicht erfüllt: wie könnte man es lösen (inkl. UML)?]_

#### Positiv-Beispiel

![Open-Closed positives Beispiel UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/open-closed-pos.iuml)

#### Negativ-Beispiel

![Open-Closed negatives Beispiel UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/open-closed-neg.iuml)

### Analyse Liskov-Substitution- (LSP), Interface-Segreggation- (ISP), Dependency-Inversion-Principle (DIP)

_[jeweils eine Klasse als positives und negatives Beispiel für entweder LSP oder ISP oder DIP); jeweils UML der Klasse und Begründung, warum man hier das Prinzip erfüllt/nicht erfüllt wird]_ \
_[Anm.: es darf nur ein Prinzip ausgewählt werden; es darf NICHT z.B. ein positives Beispiel für LSP und ein negatives Beispiel für ISP genommen werden]_

#### Positiv-Beispiel

![Liskov positives Beispiel UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/liskov-pos.iuml)

#### Negativ-Beispiel

![Liskov negatives Beispiel UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/liskov-neg.iuml)

## 4. Weitere Prinzipien

### Analyse GRASP: Geringe Kopplung

_[jeweils eine bis jetzt noch nicht behandelte Klasse als positives und negatives Beispiel geringer Kopplung; jeweils UML Diagramm mit zusammenspielenden Klassen, Aufgabenbeschreibung und Begründung für die Umsetzung der geringen Kopplung bzw. Beschreibung, wie die Kopplung aufgelöst werden kann]_

#### Positiv-Beispiel

![Kopplung positives Beispiel UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/coupling-pos.iuml)

#### Negativ-Beispiel

![Kopplung negatives Beispiel UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/coupling-neg.iuml)

### Analyse GRASP: Hohe Kohäsion

\*[eine Klasse als positives Beispiel hoher Kohäsion; UML Diagramm und Begründung, warum die Kohäsion hoch ist]
![Kohäsion Beispiel UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/cohesion.iuml)

### Don’t Repeat Yourself (DRY)\*

_[ein Commit angeben, bei dem duplizierter Code/duplizierte Logik aufgelöst wurde; Code-Beispiele (vorher/nachher); begründen und Auswirkung beschreiben]_

## 5. Unit Tests

### 10 Unit Tests

_[Nennung von 10 Unit-Tests und Beschreibung, was getestet wird]_
| Unit Test                                         | Beschreibung                                                                                                                                     |
|---------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------|
| _TestUnit#testHashCodeTrue_                       | testet, ob die `hashCode` Funktion der `Unit` Klasse, für zwei Objekte, den selben Hashcode korrekt zurückgibt zurückgibt                        |
| _TestUnit#testEqualsResSame_                      | testet, ob die `equals` Methode der `Unit` Klasse zwei Unit Instanzen mit dem selben `value` als korrekt gleich vergleicht                       |
| _TestQuantity#testConstructorException_           | testet, ob der Konstruktur der `Quantity` Klasse fehlschlägt, sobald negative (invalide) Werte übergeben werden                                  |
| _TestQuantity#testMultiply_                       | testet, ob die `multiply` Methode der `Quantity` Klasse den Wert korrekt multipliziert                                                           |
| _TestGroceryItem#testEqualsResSelf_               | testet, ob die `equals` Methode der `GroceryItem` Klasse zwei Objekte mit dem selben `value` als gleich ansieht                                  |
| _TestWebsiteFetcher#testGetWebsiteBodyInvalidUrl_ | testet, ob die `testGetWebsiteBodyInvalidUrl` Methode der `WebsiteFetcher` Klasse eine Exception wirft bei einer invaliden URL                   |
| _TestChefkochRecipeFetcher#testGetRecipe_         | testet, ob die `testGetRecipe` Methode der `ChefkochRecipeFetcher` Klasse die richtigen Inhalte für ein Chefkoch-Rezept aus dem Internet liefert |
| _TestRecipe#testConstructorHappyPath_             | testet, ob der Konstrutor der `Recipe` Klasse nicht `null` zurückgibt                                                                            |
| _TestMeal#testGetGroceryList_                     | testet, ob die `getGroceryList` Methode der `Meal` Klasse eine korrekt aggregierte Zutatenliste zurückgibt                                       |
| _TestGroceryList#testAddItem_                     | testet, ob die `addItem` Methode der `GroceryList` Klasse korrekt eine Itme zur GroceryList hinzufügt                                            |

### ATRIP: Automatic
Die Tests wurden mittels Testautomatisierung realisiert. Dabei wurde JUnit 5 verwendet, um automatisierte Tests zu schreiben. Über die IDE IntelliJ IDEA können die Test simpel über einen Knopfdruck ausgeführt werden. Im Anschluss laufen alle Tests automatisch. Das Ergebnis der Testautomatisierung zeigt, ob ein individueller Test erfolgreich (bestanden) oder nicht erfolgreich (fehlgeschlagen) war.

### ATRIP: Thorough

_[jeweils 1 positives und negatives Beispiel zu ‘Thorough’; jeweils Code-Beispiel, Analyse und Begründung, was professionell/nicht professionell ist]_

#### positives Beispiel

Test-Klasse: `TestIngredient`

```java
  @Test
  void testEqualsResSelf() {
    // arrange
    Ingredient ingredient1 = mock(Ingredient.class);

    // act
    boolean res = ingredient1.equals(ingredient1);

    // assert
    assertTrue(res);
  }

  @Test
  void testEqualsResSame() {
    // arrange
    String value = "banana";
    Ingredient ingredient1 = new Ingredient(value);
    Ingredient ingredient2 = new Ingredient(value);

    // act
    boolean res = ingredient1.equals(ingredient2);

    // assert
    assertTrue(res);
  }

  @Test
  void testEqualsDifferent() {
    // arrange
    Ingredient ingredient1 = new Ingredient("banana");
    Ingredient ingredient2 = new Ingredient("nutella");

    // act
    boolean res = ingredient1.equals(ingredient2);

    // assert
    assertFalse(res);
  }

  @Test
  void testEqualsNull() {
    // arrange
    Ingredient ingredient1 = mock(Ingredient.class);

    // act
    boolean res = ingredient1.equals(null);

    // assert
    assertFalse(res);
  }

  @Test
  void testHashCodeTrue() {
    // arrange
    String value = "banana";
    Ingredient ingredient1 = new Ingredient(value);
    Ingredient ingredient2 = new Ingredient(value);
  
    // act
    int code1 = ingredient1.hashCode();
    int code2 = ingredient2.hashCode();
  
    // assert
    assertEquals(code1, code2);
  }

  @Test
  void testHashCodeFalse() {
    // arrange
    Ingredient ingredient1 = new Ingredient("banana");
    Ingredient ingredient2 = new Ingredient("nutella");
  
    // act
    int code1 = ingredient1.hashCode();
    int code2 = ingredient2.hashCode();
  
    // assert
    assertNotEquals(code1, code2);
  }
```
Diese Testklasse mit den dargestellten Methoden ist ein Positivbeispiel für "thorough testing". All diese Testmethoden testen verschiedene Zweige der selbstimplementieren `equals` Methode der Klasse `Ingredient`. Sie testen Vergeliche zwischen
- einer `Ingredient`-Instanz mit sich selbst
- zwei gleichen `Ingredient`-Instanzen
- zwei verschiedenen `Ingredient`-Instanzen
- `null` und einer `Ingredient`-Instanz

Damit sind alle relevanten Pfade der `equals` Methode abgedeckt. Außerdem wird auch die Umgebung getestet. Da die `equals` Methode, auf die `hashcode` Methode zugreift, können Fehler in der `equals` Methode auf Fehler in der dortigen zurückgeführt werden. Demnentsprechend müssen auch alle relevanten Pfade der `hashcode` Methode getestet werden, was hier gemacht wird.

#### negatives Beispiel
zu testende Methode: `DialogService#startMealPlanGeneration`

```java
  private void startMealPlanGeneration() {
    currentState = DialogState.MEAL_PLAN_GENERATION;

    ConsoleOutputService.rawOut("Wir generieren jetzt zusammen einen Mahlzeiten-Plan. :D");
    LocalDate startDate = ConsoleInputParser.getDate(null, null,
      "Wann soll der Plan beginnen? (DD.MM.YYYY)");
    LocalDate endDate = ConsoleInputParser.getDate(startDate, null,
      "Bis wann soll der Plan gehen (exklusiv)? (DD.MM.YYYY)");
    int people = ConsoleInputParser.getInteger(1, 99,
      "Für wie viele Leute soll der Plan generiert werden?");
    int days = startDate.until(endDate).getDays();
    ConsoleOutputService.rawOut("Ok, ich generiere einen Plan für " + days + " Tage...");

    List<Recipe> recipes = recipeRepository.getRecipes();
    List<Meal> meals = new ArrayList<>();
    for (int i = 0; i < days; i++) {
      meals.add(new Meal(recipes.get(random.nextInt(recipes.size())), people));
    }

    MealPlan mealPlan = new MealPlan(meals, startDate, endDate);

    startPostMealPlanGeneration(mealPlan, recipes);
  }
```

Die hier gezeigt Methode `DialogService#startMealPlanGeneration` wurde nicht getestet, obwohl es möglich wäre dies zu tun. Damit stellt sie ein Negativbeispiel für die "thorough testing" dar. Der gezeigt Code startet die Generierung einer Essensplans. Dazu werden verschiedene Nutzereingaben abgefordert, z.B. Start und Enddatum, Anzahl der Personen. Im Anschluss werden die Rezepte geladen und mit einer zufälligen Teilmenge wird der Essensplan erstellt. Der Essensplan und die liste an Rezepten werden im Anschluss an eine weitere Methode weitergegeben.

Da die Tests für diese Methode vollständig fehlen, werden dementsprechend auch alle Pfade nicht getestet. Dementsprechend kann nicht herausgefunden werden, wo sich logische Fehler befinden. 

### ATRIP: Professional

#### positives Beispiel

Test-Methode: `TestWebsiteFetcher#testGetWebsiteBodyInvalidUrl`
```java
  @Test
  public void testGetWebsiteBodyInvalidUrl() {
    // Given
    String invalidUrl = "any string";

    // When and Then
    assertThrows(IllegalArgumentException.class, () -> WebsiteFetcher.getWebsiteBody(invalidUrl));
  }
```

Diese Testmethode testet die `getWebsiteBody` Methode der `WebsiteFetcher` Klasse. Hierbei wird ein String, der eine invalide URL darstellt, in die zu testende Funktion übergeben. Anschließend wird die `getWebsiteBody` Methode aufgerufen und überprüft, ob die richtige Exception geworfen wird. 

Diese Testmethode ist ein Positivbeispiel für professionelle Testklassen aus mehreren Gründen:
1. Der Name der Testmethode beschreibt gut, was genau getestet wird. In diesem Fall die `getWebsiteBody` Methode bei Eingabe einer invaliden URL.
2. Die zugehörige Klasse wurde nur zu Testzwecken angelegt.
3. Im Gegensatz zu Getter- oder Setter-Methoden existiert Logik, die getestetet werden sollte. Ein Test ist dementsprechend notwendig


#### negatives Beispiel
Test-Methode: `TestUnit#getValue`

```java
  @Test
  void testGetValue() {
    // arrange
    String expected = "piece";
    Unit unit = new Unit(expected);

    // act
    String res = unit.getValue();

    // assert
    assertEquals(expected, res);
  }
```

Diese Testmethode testet die `getValue` Getter-Methode der `Unit` Klasse. Dabei wird ein `Unit` ValueObject angelegt mit einem initialen Wert. Das Ergebnis der `getValue` wird verglichen mit dem initialen Wert. Beide Werten sollten gleich sein.  

Diese Klasse ist ein Negativbeispiel, da sie einen unnötigen Test darstellt. Getter Methoden sollten nicht getestet werden. Des Weiteren enthält diese Methode keine komplexe Logik, die ein Testen erfordern würde. Es handelt sich hier um einen Test, "der nur wegen des Tests geschrieben wurde". Außerdem ist der Dokumentationswert der Methode nicht vorhanden.

### Code Coverage

Die folgende Tabelle zeigt die summierten Werte der verschiedenen Arten von Testabdeckung des Projektes. Eine aufgeschlüsselte Version der Testabdeckung ist im folgenden Bild zu sehen. Die Prozentangaben des Bildes folgen derselben Reihenfolge wie in der Tabelle aufgelistet. 

| Art             | %  |
|-----------------|----|
| Class-Coverage  | 70 |
| Method-Coverage | 66 |
| Line-Coverage   | 51 |

![test coverage](./docs/res/cov.png)

Im Allgemeinen bestand das Ziel, die Testabdeckung so hoch wie möglich zu halten. Deshalb wurden weitesgehend alle Klassen automatisiert getestet, jedoch nicht alle. Das hat vor allem den Grund, dass die automatisierte Testung für einige Klassen unserer Ansicht nicht sinnhaft war. Beispiele hierfür sind:

- `domain.util.Formats`: Diese Klasse dient lediglich als Format-Klasse ohne weitere Logik. 
- `domain.util.io.ConsoleOutputService`: Da es sich hier vor allem um die Formattierung von Konsolenausgaben handelt, wurden hier die Tests ebenso vernachlässigt.

Unabhängig dessen begründet sich die Code-Coverage wie folgt:
- Class-Coverage: die Mehrheit der Klassen weist Tests auf, weshalb der Wert hier mit 70% vergleichsweise hoch liegt. 
- Method-Coverage: Aus genannten Gründen wurden eine Reihe von Methoden nicht getestet, daher liegt die Method-Coverage unter der Class-Coverage.
- Line-Coverage: Die Line-Coverage ist durch die Method-Coverage bedingt und ist deshalb vor allem aus demselbigen Grund niedriger.

### Fakes und Mocks

In diesem Projekt wurden vor allem Mock-Objekte eingesetzt. Sie wurden genutzt, um benötigte Nebenklassen zu mocken. Nachfolgend sind zwei demonstrative Beispiel für den Einsatz von Mock-Objekten mit dazugehörigen UML Diagrammen zu sehen. 

Beispiel aus: `TestGroceryList#testConstructorVarArgs`
```java
  @Test
  public void testConstructorVarArgs() {
    // arrange
    GroceryItem item1 = mock(GroceryItem.class);
    GroceryItem item2 = mock(GroceryItem.class);

    // act
    GroceryList list = new GroceryList(item1, item2);

    // assert
    assertNotNull(list);
  }
```
![Fakes und Mocks Beispiel 1 UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/fake-mock-1.iuml)

_[TODO: Analyse und Begründung für Einsatz]


Beispiel aus: `TestMealPlan#setUp`
```java
  @BeforeEach
  public void setUp() {
    start = LocalDate.now();
    end = start.plusDays(5);
    int totalMeals = start.until(end).getDays();

    List<Meal> meals = new ArrayList<>();
    for (int i = 0; i < totalMeals; i++) {
    meals.add(mock(Meal.class));
    }

    mealPlan = new MealPlan(meals, start, end);
  }
```

![Fakes und Mocks Beispiel 2 UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/fake-mock-2.iuml)

_[TODO: Analyse und Begründung für Einsatz]

_[Analyse und Begründung des Einsatzes von 2 Fake/Mock-Objekten; zusätzlich jeweils UML Diagramm der Klasse]_



## 6. Domain Driven Design

### Ubiquitous Language

_[4 Beispiele für die Ubiquitous Language; jeweils Bezeichung, Bedeutung und kurze Begründung, warum es zur Ubiquitous Language gehört]_
| Bezeichnung | Bedeutung | Begründung |
|-------------|-----------|------------|
|             |           |            |
|             |           |            |
|             |           |            |
|             |           |            |

### Entities

_[UML, Beschreibung und Begründung des Einsatzes einer Entity; falls keine Entity vorhanden: ausführliche Begründung, warum es keines geben kann/hier nicht sinnvoll ist]_
![Entity Beispiel UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/entity.iuml)

### Value Objects

_[UML, Beschreibung und Begründung des Einsatzes eines Value Objects; falls kein Value Object vorhanden: ausführliche Begründung, warum es keines geben kann/hier nicht sinnvoll ist]_
![Entity Beispiel UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/value-object.iuml)

### Repositories

_[UML, Beschreibung und Begründung des Einsatzes eines Repositories; falls kein Repository vorhanden: ausführliche Begründung, warum es keines geben kann/hier nicht sinnvoll ist]_
![Repository Beispiel UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/repository.iuml)

### Aggregates

_[UML, Beschreibung und Begründung des Einsatzes eines Aggregates; falls kein Aggregate vorhanden: ausführliche Begründung, warum es keines geben kann/hier nicht sinnvoll ist]_
![Aggregate Beispiel UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/aggregate.iuml)

## 7. Refactoring

### Code Smells

_[jeweils 1 Code-Beispiel zu 2 Code Smells aus der Vorlesung; jeweils Code-Beispiel und einen möglichen Lösungsweg bzw. den genommen Lösungsweg beschreiben (inkl. (Pseudo-)Code)]_

### 2 Refactorings

_[2 unterschiedliche Refactorings aus der Vorlesung anwenden, begründen, sowie UML vorher/nachher liefern; jeweils auf die Commits verweisen]_

#### Refactoring 1

![Refactoring Beispiel 1 Pre UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/refactoring-1-pre.iuml)
![Refactoring Beispiel 1 Post UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/refactoring-1-post.iuml)

#### Refactoring 2

![Refactoring Beispiel 2 Pre UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/refactoring-2-pre.iuml)
![Refactoring Beispiel 2 Post UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/refactoring-2-post.iuml)

## 8. Entwurfsmuster

_[2 unterschiedliche Entwurfsmuster aus der Vorlesung (oder nach Absprache auch andere) jeweils sinnvoll einsetzen, begründen und UML-Diagramm]_

#### Entwurfsmuster: [Name]

![Entwurfstmuster 1 Beispiel UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/design-pattern-1.iuml)

#### Entwurfsmuster: [Name]

![Entwurfstmuster 2 Beispiel UML](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/ncryptedV1/AutoChef/docs/uml/design-pattern-1.iuml)
