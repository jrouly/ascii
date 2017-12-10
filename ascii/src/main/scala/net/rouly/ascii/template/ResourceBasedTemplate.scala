package net.rouly.ascii.template

import scala.io.Source
import scala.util.Properties.lineSeparator

abstract class ResourceBasedTemplate(protected val resourceName: String) extends Template {

  protected val content: String =
    Source
      .fromResource(resourceName)
      .getLines()
      .toList
      .mkString("", lineSeparator, lineSeparator)

}
