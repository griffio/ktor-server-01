# Ktor Server example deployed on Render Cloud Hosting

https://render.com/

### Free Tier usage

Provides just enough RAM and CPU 512MB/0.1 for JVM app

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

* Stage 1 - Run the gradle build to create a fatJar (or use installDist) with all dependencies
* Stage 2 - Containerize the executable jar

This is because Render service checkouts the repo and executes a Docker build - yet to support deploy from Container registry
see https://feedback.render.com/features/p/deploy-docker-images-from-public-private-registries

Deploy Application log on start-up

The default JVM ergonomics uses Heap at 25% of RAM and 1 available CPU uses SerialGC

```
-XX:InitialHeapSize=8388608 -XX:MaxHeapSize=134217728 -XX:MinHeapSize=6815736 -XX:+PrintCommandLineFlags -XX:ReservedCodeCacheSize=251658240 -XX:+SegmentedCodeCache -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseSerialGC
 [info][gc,init] Version: 17.0.2+8-86 (release)
 [info][gc,init] CPUs: 8 total, 1 available
 [info][gc,init] Memory: 512M
 [info][gc,init] Large Page Support: Disabled
 [info][gc,init] NUMA Support: Disabled
 [info][gc,init] Compressed Oops: Enabled (32-bit)
 [info][gc,init] Heap Min Capacity: 8M
 [info][gc,init] Heap Initial Capacity: 8M
 [info][gc,init] Heap Max Capacity: 128M
 [info][gc,init] Pre-touch: Disabled
 [main] INFO  ktor.application - Application started in 8.185 seconds.
 [DefaultDispatcher-worker-2] INFO  ktor.application - Responding at http://0.0.0.0:8080
 [main] INFO  ktor.application - Autoreload is disabled because the development mode is off.
 [main] INFO  ktor.application - Application started in 7.4 seconds.
 [DefaultDispatcher-worker-1] INFO  ktor.application - Responding at http://0.0.0.0:8080
 [eventLoopGroupProxy-4-1] TRACE io.ktor.routing.Routing - Trace for [health]
```

### Alternatives without Credit Card

* https://www.koyeb.com/docs/faqs/pricing#is-there-a-free-tier
  * One free web Service in the Frankfurt or Washington, D.C. regions with 512MB of RAM, 0.1 vCPU, and 2GB of SSD.
* ~~https://mogenius.com~~
  * Free signups currently suspended
  * Supports DockerHub and other container registries
* https://fly.io
  * Free limited to 256MB
  * Supports container registries
