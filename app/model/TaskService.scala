package model

import javax.inject.Inject

import anorm._
import anorm.SqlParser._

import play.api.db._


/**
  * Created by andrewvarnerin on 2/5/17.
  */
class TaskService @Inject() (val dbApi: DBApi) {
  val db = dbApi.database("default")

  val simple: RowParser[Task] = {
    get[Int]("id") ~ get[String]("text") map {
      case id~text => Task(id, text)
    }
  }

  def list(): List[Task] = {
    db.withConnection { implicit c =>
      SQL("SELECT id, text FROM tasks ORDER BY id DESC").as(simple *)
    }
  }

  def count(): Int = {
    db.withConnection { implicit c =>
      SQL("select count(*) from tasks").as(SqlParser.int(1).single)
    }

  }
}
