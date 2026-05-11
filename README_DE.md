[🇬🇧 English Version](README.md)

# Konsolen-Memory-Spiel

Ein einfaches, konsolenbasiertes Memory-Kartenspiel in Java.

## Über das Projekt

Dieses Projekt ist mein erstes vollständig selbstständig entwickeltes Programm. Das Projekt dient bewusst als Rückblick auf meinen unerfahrenen Programmierstil und dokumentiert meine Entwicklung im Bereich Softwareentwicklung. Entsprechend finden sich im Code noch strukturelle Schwächen, ineffiziente Lösungen und kleine Fehler.

## Features

* **1-gegen-1-Spielmodus**
  Zwei Spieler spielen lokal an derselben Tastatur gegeneinander. Ziel ist es, möglichst viele Kartenpaare aufzudecken.

* **Mehrere Spielfeldgrößen**
  Vor Spielbeginn kann zwischen drei Schwierigkeitsgraden gewählt werden:

  * `4x4` → 16 Karten
  * `6x6` → 36 Karten
  * `8x8` → 64 Karten

* **Konsolenbasierte Benutzeroberfläche**
  Das Spiel läuft vollständig in der Konsole.
  Die Steuerung erfolgt über:

  * `W`, `A`, `S`, `D` → Bewegung
  * `F` → Karte umdrehen
    Jede Eingabe wird mit `Enter` bestätigt.

* **Automatische Spielauswertung**
  Sobald alle Kartenpaare gefunden wurden, wird das Spiel beendet und der Gewinner ausgegeben.

---

## Voraussetzungen

* **Java:** JDK 11 oder höher
* **Betriebssystem:** Windows, macOS oder Linux

---

## Installation & Start

1. Repository klonen oder herunterladen
2. In das Projektverzeichnis wechseln:

```bash
cd console-memory-game
```

3. Java-Dateien kompilieren:

```bash
javac src/*.java
```

4. Spiel starten:

```bash
java -cp src Game
```

---

## Spieloberfläche

Das Spiel verwendet ausschließlich erweiterte ASCII-Zeichen zur Darstellung des Spielfelds.

### Kartenrückseite

```text
õ
```

* ASCII-Code: `245`

### Kartenvorderseiten

Die Kartenvorderseiten basieren auf ASCII-Zeichen ab Dezimalwert `65` (`A`).

| Spielfeld | Karten | Zeichenbereich                        |
| --------- | ------ | ------------------------------------- |
| 4x4       | 16     | A–H                                   |
| 6x6       | 36     | A–R                                   |
| 8x8       | 64     | A–Z, `[`, `\`, `]`, `^`, `_`, `` ` `` |

### Cursor-Anzeige

Befindet sich der Spieler aktuell auf einer Karte, wird diese mit Klammern markiert:

```text
(õ)
(A)
```

Dadurch wird angezeigt, welche Karte aktuell ausgewählt ist und mit `F` umgedreht werden kann.

---

## Spielanleitung

### 1. Spiel starten

Programm wie oben beschrieben ausführen.

### 2. Spielfeldgröße wählen

Wähle zwischen:

* `4` → 4x4 Spielfeld
* `6` → 6x6 Spielfeld
* `8` → 8x8 Spielfeld

![](assets/menue-rdme.png)

### 3. Navigation

Bewege den Cursor mit den WASD-Tasten:

* `W` → Hoch
* `A` → Links
* `S` → Runter
* `D` → Rechts

![](assets/gameplayInit-rdme.png)

### 4. Karten umdrehen

Mit `F` wird die aktuell ausgewählte Karte aufgedeckt.

![](assets/gameplayUI-rdme.png)

### 5. Kartenpaare finden

* Pro Zug dürfen zwei Karten aufgedeckt werden.
* Stimmen beide Karten überein:

  * bleibt das Paar sichtbar
  * der Spieler erhält einen Punkt
  * der Spieler ist erneut am Zug

### 6. Spiel gewinnen

Das Spiel endet automatisch, sobald alle Kartenpaare gefunden wurden.
Der Spieler mit den meisten Punkten gewinnt.

![](assets/gameplayResult-rdme.png)

---

## Spielregeln

* Es spielen **Spieler 1** und **Spieler 2**
* Spieler 1 beginnt immer
* Jeder Zug besteht aus genau zwei aufgedeckten Karten
* Stimmen beide Karten überein:

  * bleiben sie sichtbar
  * der Spieler erhält einen Punkt
  * der Spieler darf erneut spielen
* Stimmen die Karten nicht überein:

  * werden sie wieder verdeckt
  * der nächste Spieler ist am Zug
* Das Spiel endet, wenn alle Paare gefunden wurden

---

## Hinweis zur Darstellung

Für das beste Spielerlebnis empfiehlt es sich, die Größe des Konsolenfensters manuell an das gewählte Spielfeld anzupassen. Dadurch bleibt immer nur der aktuelle Spielzustand sichtbar und die Bewegung über das Spielfeld wirkt deutlich flüssiger und übersichtlicher.

---

## Projektstruktur

```text
console-memory-game/
├── assets/
│   ├── ...
├── src/
│   ├── Game.java      # Hauptlogik und Spielsteuerung
│   ├── Player.java    # Spielerlogik und Bewegung
│   └── Card.java      # Kartenmodell
├── README_DE.md
├── README.md
├── LICENSE
└── .gitignore
```

---

## Lizenz

Dieses Projekt steht unter der MIT-Lizenz.