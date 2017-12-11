package net.rouly.ascii.play

import com.softwaremill.macwire.wire
import com.typesafe.config.{Config, ConfigFactory}
import net.rouly.ascii.play.BeeComponents.AsciiAppConfig
import net.rouly.ascii.play.module.{AsciiControllers, AsciiPlayApplication}
import net.rouly.common.config.decorators._
import net.rouly.common.server.play.filters.ApplicationHttpFilters
import net.rouly.common.server.play.module._
import play.api.ApplicationLoader.Context
import play.api._
import play.api.routing.Router
import play.filters.HttpFiltersComponents
import router.Routes

class AsciiLoader extends AppServerLoader {
  def buildComponents(context: Context): AsciiComponents =
    new BuiltInComponentsFromContext(context) with AsciiComponents
}

trait AsciiComponents
  extends AppServerComponents
  with AsciiControllers
  with HttpFiltersComponents {
  self: BuiltInComponents =>

  lazy val prefix = "/" // routing prefix

  override lazy val router: Router = wire[Routes]
  override lazy val httpFilters = new ApplicationHttpFilters(
    ApplicationHttpFilters.commonFilters: _*
  ).filters

  lazy val config = ConfigFactory.defaultApplication
  lazy val appConfig = wire[AsciiAppConfig]

  override lazy val asciiApplication: AsciiPlayApplication = wire[AsciiPlayApplication]

}

object BeeComponents {
  class AsciiAppConfig(protected val config: Config)
    extends PlayConfiguration
    with EnvironmentConfiguration
    with PropertiesConfiguration
    with LoggingConfiguration
}
