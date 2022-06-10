package com.example.domain.common.core.service

import org.junit.Assert.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object DefaultTimeFormatServiceTest : Spek({

	val service = DefaultTimeFormatService()
	val toPatternList = listOf(
		"12:12:12",
		"11:11:11",
		"05:05:05",
		"01:01:01"
	)
	val fromPatternList = listOf(
		43932000L,
		40271000L,
		18305000L,
		3661000L
	)

	describe("convert from time format") {
		toPatternList.forEachIndexed { index, item ->
			it("must return expected result") {
				assertEquals(service.fromPattern(item), fromPatternList[index])
			}
		}
	}

	describe("convert to time format") {
		fromPatternList.forEachIndexed { index, item ->
			it("must return expected result") {
				assertEquals(service.toPattern(item), toPatternList[index])
			}
		}
	}
})
