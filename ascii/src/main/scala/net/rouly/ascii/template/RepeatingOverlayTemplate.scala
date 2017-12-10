package net.rouly.ascii.template

trait RepeatingOverlayTemplate extends Template {

  override def apply(overlay: String): String =
    if (overlay.isEmpty) content
    else repeat(overlay)

  private def repeat(overlay: String): String = {
    val buffer = Stream.continually(overlay).flatten

    val (ws, cs) = content
      .zipWithIndex
      .partition(_._1.isWhitespace)

    val os = cs
      .zip(buffer)
      .map { case ((_, i), o) => o -> i }

    (ws ++ os)
      .sortBy(_._2)
      .map(_._1)
      .mkString
  }

}
