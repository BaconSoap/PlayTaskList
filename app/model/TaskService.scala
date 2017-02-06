package model

/**
  * Created by andrewvarnerin on 2/5/17.
  */
class TaskService {
  def list(): List[Task] = {
    List(Task(1, "do something"), Task(2, "Do Nothing"))
  }
}
