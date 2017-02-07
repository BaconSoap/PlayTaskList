package model

import javax.inject.Inject

import anorm._
import anorm.SqlParser._

import play.api.db._


/**
  * Created by andrewvarnerin on 2/5/17.
  */
class TaskService @Inject() (val dbApi: DBApi) {
  def list(): List[Task] = {
    List(Task(1, "do something"), Task(2, "Do Nothing"))
  }
  def count(): Int = {
    val db = dbApi.database("default")
    db.withConnection { implicit c =>
      SQL("select count(*) from tasks").as(SqlParser.int(1).single)
    }

  }
}
