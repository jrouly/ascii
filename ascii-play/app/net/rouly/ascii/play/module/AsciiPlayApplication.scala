package net.rouly.ascii.play.module

import com.softwaremill.macwire.Module
import net.rouly.ascii.template.{RepeatingOverlayTemplate, ResourceBasedTemplate, Template}
import net.rouly.common.config.Configuration

@Module
class AsciiPlayApplication(configuration: Configuration) {

  private lazy val templateName = configuration.get("template.name", "default")
  lazy val template: Template = new ResourceBasedTemplate(templateName) with RepeatingOverlayTemplate

}
