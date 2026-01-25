FROM ruby:3.3.9

# Install build dependencies and SQLite development libraries
RUN apt-get update -qq && apt-get install -y \
    libpq-dev \
    build-essential \
    libsqlite3-dev

# Install Node.js and Yarn
RUN curl -fsSL https://deb.nodesource.com/setup_24.x | bash - && \
    apt-get install --no-install-recommends -y nodejs && \
    npm install -g yarn && \
    rm -rf /var/lib/apt/lists /var/cache/apt/archives

# Install Java (required for Gradle)
RUN apt-get update -qq && apt-get install -y default-jdk && \
    rm -rf /var/lib/apt/lists /var/cache/apt/archives

# Install Gradle
ENV GRADLE_VERSION=8.12
RUN curl -fsSL https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip -o /tmp/gradle.zip && \
    unzip /tmp/gradle.zip -d /opt && \
    rm /tmp/gradle.zip && \
    ln -s /opt/gradle-${GRADLE_VERSION}/bin/gradle /usr/local/bin/gradle

# Install Kotlin
ENV KOTLIN_VERSION=2.1.10
RUN curl -fsSL "https://github.com/JetBrains/kotlin/releases/download/v${KOTLIN_VERSION}/kotlin-compiler-${KOTLIN_VERSION}.zip" -o /tmp/kotlin.zip && \
    unzip /tmp/kotlin.zip -d /opt && \
    rm /tmp/kotlin.zip && \
    ln -s /opt/kotlinc/bin/kotlin /usr/local/bin/kotlin && \
    ln -s /opt/kotlinc/bin/kotlinc /usr/local/bin/kotlinc

RUN gem install minitest

# Create a non-root user "vscode"
RUN useradd -ms /bin/bash vscode

# Set the working directory and change ownership to the vscode user
WORKDIR /workspace
RUN chown -R vscode:vscode /workspace

# Switch to the non-root user
USER vscode