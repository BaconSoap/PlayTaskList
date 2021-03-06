package controllers

import javax.inject._

import model.{Task, TaskService}
import play.api.mvc._
import play.api.libs.json._
/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class TaskController @Inject() (taskService: TaskService) extends Controller {


  implicit val taskFormat: OFormat[Task] = Json.format[Task]

  def index = Action {
    Ok( Json.toJson(taskService.list()))
  }

  def count = Action {
    Ok(taskService.count().toString)
  }

  def saveTask: Action[JsValue] = Action(BodyParsers.parse.json) { request =>
    val taskResult = request.body.validate[Task]
    taskResult.fold(
      errors => {
        BadRequest(Json.obj("status" ->"KO", "message" -> JsError.toJson(errors)))
      },
      task => {
        val newTask = taskService.create(task)
        Ok(Json.obj("status" ->"OK", "message" -> (s"Task with id '${newTask.id}" + "' saved."), "newTask" -> newTask ))
      }
    )
  }

  def getTask(id: Int) = Action {
    Ok(Json.toJson(taskService.getTask(id)))
  }


}
