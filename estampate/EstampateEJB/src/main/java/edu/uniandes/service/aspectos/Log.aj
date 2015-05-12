package edu.uniandes.service.aspectos;

public aspect Log {
	public pointcut methodCall(): call(public* edu.uniandes.service.ws.*());

	before(): methodCall() {
		System.out.println("\n -- TestClass."
				+ thisJoinPointStaticPart.getSignature().getName()
				+ " inicio --");
	}

	after(): methodCall() {
		System.out.println(" -- TestClass."
				+ thisJoinPointStaticPart.getSignature().getName()
				+ " fin --\n");
	}
}