FROM billygoo/tomcat8-jdk8
USER root
WORKDIR /home/manager/Documents/SVNDemo/
#LABEL maintainer="Akshada:Sonal"
# Install OpenJDK-8
RUN apt-get update && \
    apt-get install -y openjdk-8-jdk && \
    apt-get install -y ant && \
    apt-get clean;

# Fix certificate issues
RUN apt-get update && \
    apt-get install ca-certificates-java && \
    apt-get clean && \
    update-ca-certificates -f;

# Setup JAVA_HOME -- useful for docker commandline
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/
RUN export JAVA_HOME

ADD https://github.com/Akshu-ugale/NewGitRepo/tree/master/devops/SQS_Gizmo_RNG.war /home/manager/Documents/SVNDemo/ 

ADD https://github.com/Akshu-ugale/NewGitRepo/tree/master/GizmoApp /home/manager/Documents/SVNDemo/

ADD https://github.com/Akshu-ugale/NewGitRepo/tree/master/MavenGizmoWeb /home/manager/Documents/SVNDemo/
