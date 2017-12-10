package net.rouly.ascii.util

import org.scalatest.{FunSpec, Matchers, OptionValues}

class MutableCircularQueueSpec
  extends FunSpec
  with OptionValues
  with Matchers {

  describe("enqueue") {
    it("should enqueue at the current tail") {
      val q = MutableCircularQueue.empty[Int]
      q.enqueue(1) // 1
      q.dequeue shouldEqual 1
      q.dequeue shouldEqual 1
      q.enqueue(2) // 1, 2
      q.enqueue(3) // 1, 2, 3
      q.dequeue shouldEqual 1 // 2, 3, 1
      q.enqueue(4) // 2, 3, 1, 4
      q.dequeue shouldEqual 2
      q.dequeue shouldEqual 3
      q.dequeue shouldEqual 1
      q.dequeue shouldEqual 4
    }
  }

  describe("dequeue") {
    it("should dequeue a mutable circular queue in a loop") {
      val q = MutableCircularQueue(1, 2, 3)
      q.dequeue shouldEqual 1
      q.dequeue shouldEqual 2
      q.dequeue shouldEqual 3
      q.dequeue shouldEqual 1
      q.dequeue shouldEqual 2
      q.dequeue shouldEqual 3
    }
  }

  describe("dequeueOption") {
    it("should return None for an empty MutableCircularQueue") {
      MutableCircularQueue.empty[String].dequeueOption shouldBe empty
    }
  }

  describe("head") {
    it("should throw an exception for an MutableCircularQueue") {
      intercept[NoSuchElementException](MutableCircularQueue.empty[String].head)
    }

    it("should return the head for a non-empty MutableCircularQueue") {
      MutableCircularQueue(1, 2, 3).head shouldBe 1
    }
  }

  describe("headOption") {
    it("should return None for an empty MutableCircularQueue") {
      MutableCircularQueue.empty[String].headOption shouldBe empty
    }

    it("should return the head for a non-empty MutableCircularQueue") {
      MutableCircularQueue(1, 2, 3).headOption.value shouldBe 1
    }
  }
}
