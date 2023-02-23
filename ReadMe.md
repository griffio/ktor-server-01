# Ktor Server example deployed on Render Cloud Hosting

https://render.com/

* Link GitHub account to Render 
* Allow access to the GitHub repo
* Create a new Render Web Service for Docker
* Set health check to /health
* Leave Dockerfile settings to defaults

### Multistage Dockerfile
This is because Render service checkouts the repo and executes a Docker build - support for Container registry
see https://feedback.render.com/features/p/deploy-docker-images-from-public-private-registries

Deploy Application log on start-up
```
 [main] INFO  ktor.application - Application started in 8.185 seconds.
 [DefaultDispatcher-worker-2] INFO  ktor.application - Responding at http://0.0.0.0:8080
 [main] INFO  ktor.application - Autoreload is disabled because the development mode is off.
 [main] INFO  ktor.application - Application started in 7.4 seconds.
 [DefaultDispatcher-worker-1] INFO  ktor.application - Responding at http://0.0.0.0:8080
 [eventLoopGroupProxy-4-1] TRACE io.ktor.routing.Routing - Trace for [health]
```
