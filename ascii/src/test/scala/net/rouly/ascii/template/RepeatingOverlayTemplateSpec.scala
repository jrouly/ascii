package net.rouly.ascii.template

import org.scalatest.{FunSpec, Matchers}

class RepeatingOverlayTemplateSpec
  extends FunSpec
  with Matchers {

  describe("apply") {

    it("should return the underlying template if an empty overlay is given") {
      val template: Template = new StringBasedTemplate(" * * * * * * * * * * * * ") with RepeatingOverlayTemplate
      template("") shouldEqual " * * * * * * * * * * * * "
    }

    it("should repeatedly apply the overlay") {
      val template: Template = new StringBasedTemplate(" 1 2 3 4 5 6 7 8 9 0 1 2 ") with RepeatingOverlayTemplate
      template("michel") shouldEqual " m i c h e l m i c h e l "
    }

    it("should repeatedly apply the overlay to a multiline string") {
      val template: Template = new StringBasedTemplate("""     *
        |    ***
        |   *****
        |  *******
        |     *""".stripMargin) with RepeatingOverlayTemplate

      template("michel") shouldEqual
        """     m
          |    ich
          |   elmic
          |  helmich
          |     e""".stripMargin
    }

    it("should render templates from a resource file") {
      val template: Template = new ResourceBasedTemplate("templates/test.template") with RepeatingOverlayTemplate
      val expected =
        "m           i\n" +
          "    c           h\n" +
          "       e           l\n" +
          "          m          i\n" +
          "            c          h\n" +
          "              e          l                   mic\n" +
          "               h          e                lmiche\n" +
          "            lmiche  lmichelmi            chelmich\n" +
          "         elmichelmichelm   ichelmi     chelmichel         michel\n" +
          "        michelmichelmichelmichelmich  elmichelmic    helmichelmichelmi\n" +
          "        chelmichelmichelmichelmichelmichelmichelmichelmichelmichelmiche\n" +
          "     lmichelmichelmichelmichelmichelmichelmichelmichelmichelmichelmich\n" +
          "   elmichelmichelmichelmichelmichelmichelmichelmichelmichelmichelmi\n" +
          "  chelmichelmichelmichelmichelmic helmichelmichelmichelmichelmi\n" +
          " chelmichelmichelmichelmiche l m iche lmichelmichelmichelm\n" +
          " ichelmichelmichelmichelmichelmichelmichelmichelmichelmichelmi\n" +
          "  chelmichelmichelmichelmichelmichelmichelmichelmichelmichelmic\n" +
          "    helmichelmichelmichelmichelmichelmichelmichelmichelmichel\n" +
          "       michelmichelmichelmichelmichelmichelmichelmichelmich\n" +
          "       elmichelmichelmichelmichelmichelmichelmichelmichel\n" +
          "     michelmichelmichelmichelmichelmichelmi      chelm\n" +
          "    ichelmichelmichelmichelmichelmichelmich      elmic\n" +
          "   helmichel       michelmichelmichelmichelm    ichelmi\n" +
          "  chelmichelm         ichelmichelmichelmiche      lmi\n" +
          "   chelmiche           lmichelmichelmichelmi\n" +
          "     chel              michelmichelmichelmic\n" +
          "                      helmichelmichelmichelm\n" +
          "                     ichelmichelmichelmiche\n" +
          "                     lmichelmichelmichelmi\n" +
          "                     chelmichelmichelmich\n" +
          "                     elmichelmichelmiche\n" +
          "                      lmichelmichelmic\n" +
          "                       helmichelmich\n" +
          "                        elmichelm\n" +
          "                         iche\n" +
          "                         lmi\n" +
          "                        ch\n" +
          "                       e\n"
      template("michel") shouldEqual expected
    }
  }
}
