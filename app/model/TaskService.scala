package model

import javax.inject.Inject

import anorm._
import anorm.SqlParser._

import play.api.db._


/**
  * Created by andrewvarnerin on 2/5/17.
  */
class TaskService @Inject() (val dbApi: DBApi) {
  val db: Database = dbApi.database("default")

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

  /**
    * get a
    * @return
    */
  def count(): Int = {
    db.withConnection { implicit c =>
      SQL("select count(*) from tasks").as(SqlParser.int(1).single)
    }
  }

  /**
    * Saves a task in the db
    * @param task Task to save. task.id will be ignored
    * @return a new Task wih the id field populated
    */
  def create(task: Task): Task = {
    val insertSql = "INSERT INTO tasks (text) VALUES ({text})"
    val idMaybe: Option[Long] = db.withConnection {implicit c =>
      SQL(insertSql).on("text" -> task.text).executeInsert()
    }

    val id = idMaybe match {
      case Some(i) => i
      case None => throw new Exception("couldn't create task")
    }

    task.copy(id = id.toInt)
  }

  /**
    * Retrieve a task from the db by its id
    * @param id pk of the task to look up
    * @return
    */
  def getTask(id: Int): Task = {
    db.withConnection {implicit c =>
      SQL("SELECT id, text FROM tasks WHERE id = {id}").on("id" -> id).as(simple.single)
    }
  }
}
