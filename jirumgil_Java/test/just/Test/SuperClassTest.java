package just.Test;

import junit.framework.TestCase;

public class SuperClassTest extends TestCase {
	public void testConstructorCalled() {
		SuperClass superClass = new SubClass("parm");
		assertTrue(SuperClass.constructorWasCalled);
	}
}
