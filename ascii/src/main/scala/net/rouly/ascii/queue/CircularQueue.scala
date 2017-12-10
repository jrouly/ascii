package net.rouly.ascii.queue

sealed class CircularQueue[T] protected (protected val list: List[T]) {

  def enqueue(elem: T): CircularQueue[T] = new CircularQueue[T](list :+ elem)

  def dequeue: (T, CircularQueue[T]) = list match {
    case Nil => throw new NoSuchElementException("dequeue on empty queue")
    case t :: Nil => (t, this)
    case t :: ts => (t, new CircularQueue(list.tail :+ t))
  }

  def head: T = list match {
    case Nil => throw new NoSuchElementException("head on empty queue")
    case t :: ts => t
  }

  def headOption: Option[T] = safely(head)

  def dequeueOption: Option[(T, CircularQueue[T])] = safely(dequeue)

  private def safely[A](a: => A): Option[A] = if (list.isEmpty) None else Some(a)
}

object CircularQueue {
  def empty[T] = new CircularQueue(List.empty[T])
  def apply[T](ts: T*) = new CircularQueue[T](ts.toList)
}
