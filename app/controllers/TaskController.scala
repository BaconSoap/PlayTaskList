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


  implicit val taskWrites: OWrites[Task] = Json.writes[model.Task]
  implicit val taskReads: Reads[Task] = Json.reads[model.Task]

  /**
    * Create an Action to render an HTML page with a welcome message.
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def index = Action {
    Ok( Json.toJson(taskService.list()))
  }

  def saveTask: Action[JsValue] = Action(BodyParsers.parse.json) { request =>
    val taskResult = request.body.validate[Task]
    taskResult.fold(
      errors => {
        BadRequest(Json.obj("status" ->"KO", "message" -> JsError.toJson(errors)))
      },
      task => {
        //Task.save(place)
        Ok(Json.obj("status" ->"OK", "message" -> ("Task with text '"+task.text+"' saved.") ))
      }
    )
  }


}
