package net.rouly.ascii.play.controllers

import net.rouly.ascii.template.Template
import play.api.mvc.{AbstractController, ControllerComponents}

class TemplateController(
  cc: ControllerComponents,
  template: Template
) extends AbstractController(cc) {

  def renderEmpty = Action {
    Ok(template(""))
  }

  def render(text: String) = Action {
    Ok(template(text))
  }

}

