# Security course email confirmation lab (#8)
Registration and email confirmation using an SMTP service

## How to use
### Prerequisites
• Intellij or other Java IDE's  
• JDK 11+

### Pre-config

To use this application, an SMTP service should be up.

The following variables should be changed according to the SMTP service:

`smtp_host`
`smtp_port`
`smtp_username`
`smtp_password`
`email_sender`

Changes should be made here: `src/main/resources/application.yml`

### Running

Run the following command inside the root of this repository:

```
.\gradlew.bat bootRun
```

Navigate to `http://localhost:8080/` to access the app.