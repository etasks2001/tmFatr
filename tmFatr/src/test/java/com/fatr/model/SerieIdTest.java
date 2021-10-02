package com.fatr.model;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

public class SerieIdTest {
	@Test
	public void testEquals_hashCode() {
		SerieId x = new SerieId(1, 2);
		SerieId y = new SerieId(1, 2);

		MatcherAssert.assertThat(x.hashCode(), Matchers.equalTo(y.hashCode()));

		x = new SerieId(null, 2);
		y = new SerieId(null, 2);

		MatcherAssert.assertThat(x.hashCode(), Matchers.equalTo(y.hashCode()));

		x = new SerieId(1, null);
		y = new SerieId(1, null);

		MatcherAssert.assertThat(x.hashCode(), Matchers.equalTo(y.hashCode()));

	}

	@Test
	public void test_constructor() {
		SerieId x = new SerieId(1, 2);

		MatcherAssert.assertThat(x.getId_emit(), Matchers.equalTo(1));
		MatcherAssert.assertThat(x.getSerie(), Matchers.equalTo(2));
	}

	@Test
	public void test_equals() {
		SerieId x = new SerieId(1, 2);
		SerieId y = new SerieId(1, 2);
		MatcherAssert.assertThat(x.equals(y), Matchers.equalTo(true));
		MatcherAssert.assertThat(x.equals(null), Matchers.equalTo(false));
		MatcherAssert.assertThat(x.equals(""), Matchers.equalTo(false));

		x = new SerieId(null, 2);
		y = new SerieId(1, 2);
		MatcherAssert.assertThat(x.equals(y), Matchers.equalTo(false));

		x = new SerieId(1, 2);
		y = new SerieId(null, 2);
		MatcherAssert.assertThat(x.equals(y), Matchers.equalTo(false));

		x = new SerieId(null, 2);
		y = new SerieId(null, 2);
		MatcherAssert.assertThat(x.equals(y), Matchers.equalTo(true));

		x = new SerieId(1, null);
		y = new SerieId(1, 2);
		MatcherAssert.assertThat(x.equals(y), Matchers.equalTo(false));

		x = new SerieId(1, 2);
		y = new SerieId(1, null);
		MatcherAssert.assertThat(x.equals(y), Matchers.equalTo(false));

		x = new SerieId(2, null);
		y = new SerieId(2, null);
		MatcherAssert.assertThat(x.equals(y), Matchers.equalTo(true));

	}
}
