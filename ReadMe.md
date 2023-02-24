# Ktor Server example deployed on Render Cloud Hosting

https://render.com/

Free Tier usage

* Link GitHub account to Render 
* Allow access to the GitHub repo
* Create a new Render Web Service for Docker
* Set a service name (will be accessible at https://[servicename].onrender.com/)
* Build & Deploy settings
* Set repo branch
* Set health check to /health
* Leave Dockerfile and other settings to defaults

### Multistage Dockerfile

[Dockerfile](https://github.com/griffio/ktor-server-01/blob/master/Dockerfile)

* Stage 1 - Run the gradle build to create fatJar with all dependencies
* Stage 2 - Containerize the executable jar

This is because Render service checkouts the repo and executes a Docker build - yet to support deploy from Container registry
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

### Alternatives without Credit Card

* https://mogenius.com/home
  * Free signups currently suspended
  * Supports DockerHub and other container registries
