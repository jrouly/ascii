package net.rouly.ascii.template

import scala.io.Source
import scala.util.Properties.lineSeparator

abstract class ResourceBasedTemplate(protected val resourceName: String) extends Template {
  import ResourceBasedTemplate.find

  protected val content: String =
    Source
      .fromResource(find(resourceName))
      .getLines()
      .toList
      .mkString("", lineSeparator, lineSeparator)

}

object ResourceBasedTemplate {
  private def find(name: String): String = s"templates/$name.template"
}
