package net.rouly.ascii.template

import net.rouly.ascii.util._

trait CircularQueueTemplate extends Template {

  override def apply(overlay: String) = {
    val q: MutableCircularQueue[Char] = MutableCircularQueue(overlay: _*)
    content.map(c => if (c.isWhitespace) c else q.dequeueOption.getOrElse(c))
  }

}
