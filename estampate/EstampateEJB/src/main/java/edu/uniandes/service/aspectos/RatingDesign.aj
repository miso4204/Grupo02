package edu.uniandes.service.aspectos;

public aspect RatingDesign {
 public pointcut methodCall():
  call(public void edit(Estampa)) && within(edu.uniandes.service.ws.*);

 before(): methodCall() {
  System.out.println("\n -- Rating de estampa");
 }

}