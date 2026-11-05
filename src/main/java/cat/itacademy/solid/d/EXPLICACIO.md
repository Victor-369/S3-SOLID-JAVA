# Anàlisi

**Quines operacions necessiten sobre les reserves?**
- save (BookingService, BookingConsole)
- findById (BookingService, BookingConsole)
- findAllOrderedByStart (BookingConsole)

**Per què rebre el repositori pel constructor encara els lliga a H2?**
Rebre el repositori pel constructor és injecció de dependencia. I, per tant, només accepta H2.

**Si tots dos repositoris ofereixen les operacions necessàries, què impedeix passar el de memòria al servei actual?**
En analitzar els dos repositoris, vaig veure que tenen els mateixos mètodes: `save`, `findById` i `findAllOrderedByStart`. 
Al principi pensava que, com que fan el mateix, podria passar qualsevol dels dos al `BookingService`. Però no funciona.
Per poder canviar d'un repositori a l'altre, caldria crear una interfície amb les operacions que necessita el servei. 
Les dues classes l'haurien d'implementar, i el servei hauria de rebre aquesta interfície en lloc de la classe d'H2.

**On s'hauria de decidir quina implementació utilitza l'aplicació?**
S'hauria d'implementar en `BookingApp`, al mètode main, que és on arrenca l'aplicació i on es creen i es connecten totes les peces.

**Per què aquestes operacions i no altres**
- findById i save: les necessita el servei per cancel·lar. També les fa servir la consola, per veure una reserva i per crear-ne una de nova.
- findAllOrderedByStart: la necessita la consola per llistar les reserves.

**On posaria la interfície**
- H2BookingRepository i InMemoryBookingRepository són les que depenen del contracte del servei, perquè l'han d'implementar.
