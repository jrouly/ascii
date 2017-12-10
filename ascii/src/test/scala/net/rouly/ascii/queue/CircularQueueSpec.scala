package net.rouly.ascii.queue

import org.scalatest.{FunSpec, Matchers, OptionValues}

class CircularQueueSpec
  extends FunSpec
  with OptionValues
  with Matchers {

  describe("enqueue") {
    it("should enqueue at the current tail") {
      CircularQueue
        .empty[Int]
        .enqueue(1)
        .dequeue._1 shouldEqual 1
    }
  }

  describe("dequeue") {
    it("should dequeue a circular queue in a loop") {
      val q0 = CircularQueue(1, 2, 3)

      val (one, q1) = q0.dequeue
      one shouldEqual 1
      q1.head shouldEqual 2

      val (two, q2) = q1.dequeue
      two shouldEqual 2
      q2.head shouldEqual 3

      val (three, q3) = q2.dequeue
      three shouldEqual 3
      q3.head shouldEqual 1

      val (value, queue) = q3.dequeue
      value shouldBe 1
      queue.head shouldEqual 2
    }
  }

  describe("dequeueOption") {
    it("should return None for an empty CircularQueue") {
      CircularQueue.empty[String].dequeueOption shouldBe empty
    }
  }

  describe("head") {
    it("should throw an exception for an CircularQueue") {
      intercept[NoSuchElementException](CircularQueue.empty[String].head)
    }

    it("should return the head for a non-empty CircularQueue") {
      CircularQueue(1, 2, 3).head shouldBe 1
    }
  }

  describe("headOption") {
    it("should return None for an empty CircularQueue") {
      CircularQueue.empty[String].headOption shouldBe empty
    }

    it("should return the head for a non-empty CircularQueue") {
      CircularQueue(1, 2, 3).headOption.value shouldBe 1
    }
  }
}
