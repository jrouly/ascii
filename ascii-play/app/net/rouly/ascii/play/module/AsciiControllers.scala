package net.rouly.ascii.play.module

import com.softwaremill.macwire.wire
import net.rouly.ascii.play.controllers.TemplateController
import play.api.mvc.ControllerComponents

trait AsciiControllers {

  def asciiApplication: AsciiPlayApplication
  def controllerComponents: ControllerComponents

  lazy val templateController: TemplateController = wire[TemplateController]

}

