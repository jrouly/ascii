package net.rouly.ascii.util

sealed class MutableCircularQueue[T] protected (protected var list: List[T]) {

  def enqueue(elem: T): Unit = synchronized {
    list = list :+ elem
  }

  def dequeue: T = synchronized {
    list match {
      case Nil => throw new NoSuchElementException("dequeue on empty queue")
      case t :: Nil => t
      case t :: ts => list = list.tail :+ t; t
    }
  }

  def head: T = synchronized {
    list match {
      case Nil => throw new NoSuchElementException("head on empty queue")
      case t :: ts => t
    }
  }

  def headOption: Option[T] = safely(head)

  def dequeueOption: Option[T] = safely(dequeue)

  private def safely[A](a: => A): Option[A] = if (list.isEmpty) None else Some(a)
}

object MutableCircularQueue {
  def empty[T] = new MutableCircularQueue(List.empty[T])
  def apply[T](ts: T*) = new MutableCircularQueue[T](ts.toList)
}
