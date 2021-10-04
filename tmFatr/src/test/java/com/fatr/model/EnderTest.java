package com.fatr.model;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

public class EnderTest {

    @Test
    public void testEquals_hashCode() {

	Ender x = EnderBuilder.builder().addxLgr("Rua sem saida").addNro("150").addxCpl("fdas").addxBairro("centro").addxMun("0000000").addcMun("são paulo").addUF("sp").addCEP("00000000")
		.addcPais("0000").addxPais("brasil").addFone("12345789").get();
	Ender y = EnderBuilder.builder().addxLgr("Rua sem saida").addNro("150").addxCpl("fdas").addxBairro("centro").addxMun("0000000").addcMun("são paulo").addUF("sp").addCEP("00000000")
		.addcPais("0000").addxPais("brasil").addFone("12345789").get();

	MatcherAssert.assertThat(x.hashCode(), Matchers.equalTo(y.hashCode()));

	x = EnderBuilder.builder().get();
	y = EnderBuilder.builder().get();
	MatcherAssert.assertThat(x.hashCode(), Matchers.equalTo(y.hashCode()));
    }

    @Test
    public void test_equals() {
	Ender x = EnderBuilder.builder().addxLgr("Rua sem saida").addNro("150").addxCpl("fdas").addxBairro("centro").addxMun("0000000").addcMun("são paulo").addUF("sp").addCEP("00000000")
		.addcPais("0000").addxPais("brasil").addFone("12345789").get();
	Ender y = EnderBuilder.builder().addxLgr("Rua sem saida").addNro("150").addxCpl("fdas").addxBairro("centro").addxMun("0000000").addcMun("são paulo").addUF("sp").addCEP("00000000")
		.addcPais("0000").addxPais("brasil").addFone("12345789").get();

	MatcherAssert.assertThat(x.equals(x), Matchers.equalTo(true));
	MatcherAssert.assertThat(x.equals(null), Matchers.equalTo(false));
	MatcherAssert.assertThat(x.equals(""), Matchers.equalTo(false));

    }
}
