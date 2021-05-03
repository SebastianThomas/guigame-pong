# Probleme
Während der Spiel-Programmierung bin ich auf Probleme verschiedenster Art gestoßen, die ich im folgenden darlegen werde: 

## Probleme mit der Idee
### Finden einer geeigneten Idee
Da ich mich für Programmieren sehr interessiere, wollte ich kein zu einfaches Spiel programmieren. 

Nach einigen Tagen Recherche kam ich auf die Idee, dass ältere Programme (wie z.B. PONG) nicht zu aufwendig sind. PONG ist eines meiner Lieblingsspiele, da es recht simpel ist, und daher entschloss ich mich, es selbst zu programmieren.

## GUI-Probleme
### Invalid Components / Windows
Immer wieder hatte ich das Problem, dass Komponenten oder ganze Fenster `invalid` waren, was nicht durch ein einfaches `revalidate()` oder `repaint()` zu lösen war. 

Die häufigste Lösung war, dass ich, anstatt beispielsweise ein altes Fenster wieder zu benutzen, das alte Fenster mit `dispose()` zerstört und ein neues Fenster erzeugt habe. 
Gleiches tat ich auch mit den Komponenten. Da die Java Runtime einen automatischen GC (Garbage Collector) nutzt, blieben die alten Komponenten nicht im Speicher, weshalb ich keinerlei Nachteile durch die Neuerzeugung hatte. 

### `Layout`-Probleme
Ebenfalls öfter hat ein zu kompliziertes Layout (z.B. `GridBagLayout`) zu Problemen geführt, da die Komponenten anders angezeigt wurden, als ich es erwartet habe. 

Als Lösung habe ich einfachere Layouts (z.B. `GridLayout`) genutzt, die einzelne `JPanel`s enthalten. Somit konnte ich das Problem in immer kleinere Probleme aufteilen. 

### Label wird nicht angezeigt
Eigentlich sollte, wenn ein Punkt vorbei ist, ein `JLabel` angezeigt werden, auf dem steht, welcher Spieler gewonnen hat. Trotz mehrerer Versuche herauszufinden, wieso es nicht angezeigt wird, habe ich das Problem noch nicht gefunden. 

Daher habe ich das Problem auch noch nicht gelöst.

## Logik-Probleme
