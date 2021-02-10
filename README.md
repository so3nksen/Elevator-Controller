# Aufgabenstellung
Schreiben Sie ein objektorientiertes Programm in Java, das die Verwaltung einer Aufzuganlage darstellt. Das Programm soll mindestens einen Aspekt der Realität mit einer Klassen-Hierarchie abbilden.
Die Aufzuganlage soll ein Haus modellieren, das 100 Stockwerke hat. Es sind dort 45 Aufzüge vorhanden:
![Eigenschaften](https://i.imgur.com/w2XUxNF.png)

Die aufzugart-spezifischen Eigenschaften werden für die einzelnen Aufzüge mit sinnvollen Werten belegt.
Die Auzugsteuerung soll folgendes erlauben: ein Nutzer kann angeben, von welchem Stockwerk zu welchem anderen Stockwerk Personen oder Güter transportiert werden sollen. Zusätzlich muss er angeben, wie viele Personen oder wie viele kg Last transportiert werden sollen.
Das Programm sucht daraufhin den Aufzug aus der am nächsten an dem Start-Stockwerk ist und der die notwendige Kapazität besitzt. Das Programm lehnt Anfragen für Gruppen mit mehr als 30 Personen und für Gütertransporte mit mehr als 10.000 kg ab.
Die Aufzüge der Art „VIP-Aufzug“ werden standardmäßig nicht verwendet. Der Nutzer muss sie explizit anfragen und mit einem Passwort seine Identität bestätigen um diese Anfragen zu können.

Zu jedem Zeitpunkt kann der Nutzer die Position aller Aufzüge vom Programm ausgeben lassen, oder den Wert eines Aufzug-Attributs verändern und abspeichern.
Ebenso kann der Nutzer sich alle Aufzüge anzeigen lassen, die einem bestimmten Wert in der aufzugart-spezifischen Eigenschaft entsprechen.
Das Programm erfasst im Hintergrund, wie viele Personen und welche Lastkapazitäten seit dem Programmstart über die Aufzüge transportiert worden sind.
Wird das Programm vom Nutzer beendet, so werden diese Daten in einer Datei auf der Festplatte gespeichert. Beim erneuten Start der Anwendung werden diese Daten genutzt um die Position und Eigenschaften der Aufzüge aus dem vorherigen Durchlauf des Programmes wiederherzustellen.
