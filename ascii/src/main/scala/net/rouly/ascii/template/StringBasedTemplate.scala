package net.rouly.ascii.template

abstract class StringBasedTemplate(protected val inputString: String) extends Template {

  protected val content: String = inputString

}
