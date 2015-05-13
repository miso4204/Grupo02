package edu.uniandes.service.aspectos;

public aspect Log {
 public pointcut methodCall():
  call(* * (..)) && within(edu.uniandes.service.ws.*);

 before(): methodCall() {
  System.out.println("\n -- Inicio --");
 }

 after(): methodCall() {
  System.out.println(" -- Finalizacion sin errores --\n");
 }
}