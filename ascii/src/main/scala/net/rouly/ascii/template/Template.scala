package net.rouly.ascii.template

trait Template {

  def apply(overlay: String): String

  protected def content: String

}
