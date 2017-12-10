package net.rouly.ascii.template.mutable

import net.rouly.ascii.queue.mutable.CircularQueue
import net.rouly.ascii.template.Template

trait RepeatingOverlayTemplate extends Template {

  override def apply(overlay: String): String = {
    val q: CircularQueue[Char] = CircularQueue(overlay: _*)
    content.map(c => if (c.isWhitespace) c else q.dequeueOption.getOrElse(c))
  }

}
