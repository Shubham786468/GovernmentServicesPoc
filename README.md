Government Service POC
Dynamic Government Service Platform using ADK + A2UI + AGenUI + AppFunctions + Clean Architecture + MVVM
Slide 1: Title
Government Service POC
AI-Ready Metadata Driven Government Services Platform

Technologies

Jetpack Compose
MVVM
Clean Architecture
Hilt
Android App Functions
Google ADK
A2UI
AGenUI
Dynamic Workflow Engine
Slide 2: Problem Statement

Traditional Government Applications are built as:

Problems:

Separate UI development
Duplicate business logic
Hardcoded workflows
Difficult onboarding of new services
High maintenance
Slide 3: Proposed Solution

Build a dynamic platform where:

Plain Text
1
Metadata
2
↓
3
Dynamic UI
4
↓
5
Workflow Engine
6
↓
7
Agents
Show more lines

New service onboarding should require:

Plain Text
1
JSON Metadata
2
 
3
Workflow Definition
4
 
5
Agents
Show more lines

instead of a complete new application.

Slide 4: Current Scope
Implemented

✅ Income Certificate

✅ Passport

✅ Dynamic UI Framework

✅ Dynamic Workflow Engine

✅ ADK Integration

✅ Android App Functions

✅ Agent Registry

✅ Navigation

Future

🔄 Driving License

🔄 Pension

🔄 Dynamic Workflow Generation through ADK

Slide 5: High Level Architecture
Slide 6: Clean Architecture

Project follows:

Plain Text
1
Presentation
2
 
3
↓
4
 
5
Domain
6
 
7
↓
8
 
9
Data
Show more lines
Presentation

Contains:

Plain Text
1
Screens
2
 
3
Navigation
4
 
5
ViewModels
Show more lines

Examples:

Domain

Contains:

Plain Text
1
UseCases
2
 
3
Workflow Engine
4
 
5
Agents
6
 
7
Models
8
 
Show more lines

Examples:

Data

Contains:

Plain Text
1
Repositories
2
 
3
Retrofit
4
 
5
DTOs
6
 
7
Mock APIs
Show more lines
Slide 7: MVVM Architecture

Flow:

Plain Text
1
Compose UI
2
 
3
↓
4
 
5
ViewModel
6
 
7
↓
8
 
9
UseCase
10
 
11
↓
12
 
13
Repository
14
 
15
↓
16
 
17
API
Show more lines

Benefits:

Separation of UI and business logic
Lifecycle aware
Testable
Scalable
Slide 8: Hilt Dependency Injection

Used for:

Plain Text
1
Repositories
2
 
3
Workflow Engine
4
 
5
Agents
6
 
7
UseCases
8
 
9
ViewModels
10
 
11
ADK Components
Show more lines

Benefits:

✅ Loose coupling

✅ Easy testing

✅ Better dependency management

Slide 9: Navigation Flow

Application starts at:

Plain Text
1
Home Screen
Show more lines

Services:

Plain Text
1
Income Certificate
2
 
3
Passport
4
 
5
Driving License
6
 
7
Pension
Show more lines

Current Status:

Slide 10: A2UI (Agent To UI)
What is A2UI?

Instead of:

Plain Text
1
Hardcoded Compose Screen
Show more lines

we use:

Plain Text
1
Metadata
2
 
3
↓
4
 
5
UI Generation
Show more lines

Example:

JSON
1
{
2
"screenId":"income_certificate",
3
"fields":[]
4
}
Show more lines

Flow:

Plain Text
1
Metadata
2
 
3
↓
4
 
5
UiMetadataAgent
6
 
7
↓
8
 
9
Renderer
10
 
11
↓
12
 
13
Compose UI
Show more lines

Benefits:

✅ Reusable UI

✅ New services without UI coding

✅ Faster development

Slide 11: A2UI in the Project
Important Classes
UiMetadataAgent

Purpose:

Plain Text
1
Loads metadata
2
and prepares UI
Show more lines
MetadataRepository

Purpose:

Plain Text
1
Reads metadata JSON
Show more lines

Examples:

Plain Text
1
income_certificate.json
2
 
3
passport.json
Show more lines
DynamicFormScreen

Purpose:

Plain Text
1
Render dynamic fields
Show more lines

without creating a specific screen.

Slide 12: Google ADK Integration
Why ADK?

Traditional:

Kotlin
1
if(query.contains("income"))
Show more lines

Hardcoded.

Instead:

Plain Text
1
Prompt
2
 
3
↓
4
 
5
Google ADK
6
 
7
↓
8
 
9
Service Identification
Show more lines
Slide 13: ADK Flow

Example:

User:

Plain Text
1
I need a passport
Show more lines

ADK:

Plain Text
1
passport
Show more lines

Flow:

Plain Text
1
Prompt
2
 
3
↓
4
 
5
ServiceSelectionAdkAgent
6
 
7
↓
8
 
9
ServiceSelectionAgent
10
 
11
↓
12
 
13
UiMetadataAgent
14
 
15
↓
16
 
17
passport.json
Show more lines
Slide 14: ADK Classes
GovernmentServiceTool

Purpose:

Plain Text
1
Provide available services
Show more lines
ServiceSelectionAdkAgent

Purpose:

Plain Text
1
LLM-powered service selection
Show more lines
AdkRunnerManager

Purpose:

ServiceSelectionAgent

Purpose:

Plain Text
1
Convert user intent
2
to serviceId
Show more lines

Examples:

Plain Text
1
income_certificate
2
 
3
passport
Show more lines
Slide 15: Benefits of ADK

✅ Natural language understanding

✅ AI-powered routing

✅ Future Gemini integration

✅ Removes service-specific code

Examples:

Plain Text
1
I need income proof
2
 
3
↓
4
 
5
income_certificate
Show more lines
Plain Text
1
I need passport
2
 
3
↓
4
 
5
passport
Show more lines
Slide 16: AGenUI (Agent Generated Workflow)
What is AGenUI?

Instead of:

Plain Text
1
Hardcoded Workflow
Show more lines

we use:

and

Slide 17: Income Certificate Workflow
Plain Text
1
ELIGIBILITY
2
 
3
↓
4
 
5
VERIFICATION
6
 
7
↓
8
 
9
APPROVAL
10
 
11
↓
12
 
13
SUBMIT
14
 
Show more lines

Agents:

Plain Text
1
EligibilityAgent
2
 
3
VerificationAgent
4
 
5
ApprovalAgent
6
 
7
SubmitApplicationAgent
Show more lines
Slide 18: Passport Workflow

or currently:

Plain Text
1
DOCUMENT_CHECK
2
 
3
↓
4
 
5
POLICE_VERIFICATION
6
 
7
↓
8
 
9
PASSPORT_APPROVAL
10
 
11
↓
12
 
13
SUBMIT
Show more lines

Agents:

Slide 19: DynamicWorkflowEngine

Purpose:

Plain Text
1
Execute workflow dynamically
Show more lines

Example:

Execution:

Plain Text
1
Get Agent
2
 
3
↓
4
 
5
Execute Agent
6
 
7
↓
8
 
9
Next Agent
Show more lines

without knowing the service.

Slide 20: AgentRegistry

One of the most important classes.

Purpose:

Plain Text
1
Store and provide
2
workflow agents
Show more lines

Current Registry:

Plain Text
1
EligibilityAgent
2
 
3
VerificationAgent
4
 
5
ApprovalAgent
6
 
7
SubmitApplicationAgent
8
 
9
DocumentCheckAgent
10
 
11
PoliceVerificationAgent
12
 
13
PassportApprovalAgent
Show more lines

Workflow Engine uses:

instead of hardcoding.

Benefits:

✅ Dynamic workflow

✅ Extensible

✅ Service independent

Slide 21: App Functions

Purpose:

Expose business functionality to:

Plain Text
1
Android Intelligence
2
 
3
Gemini
4
 
5
Future ADK tool calling
Show more lines
Slide 22: Implemented App Functions

Income Certificate:

Plain Text
1
verifyIncomeEligibility()
2
 
3
submitIncomeCertificate()
4
 
5
checkApplicationStatus()
6
 
7
generateCertificate()
Show more lines

Passport:

Plain Text
1
verifyDocuments()
2
 
3
submitPassportApplication()
4
 
5
schedulePoliceVerification()
6
 
7
generateAcknowledgement()
Show more lines
Slide 23: AppFunctionRegistry

Purpose:

Execute app functions dynamically.

Flow:

Example:

JSON
1
{
2
"appFunction":
3
"submitPassportApplication"
4
}
Show more lines

Benefits:

✅ Dynamic actions

✅ AI integration ready

✅ Reusable

Slide 24: Important Domain Classes
DynamicWorkflowEngine

Workflow execution engine.

AgentRegistry

Provides workflow agents.

SubmitApplicationUseCase

Income Certificate submission.

SubmitPassportUseCase

Passport submission.

EligibilityUseCase

Eligibility validation.

VerifyDocumentsUseCase

Passport document validation.

Slide 25: Dynamic Metadata

Income:

Plain Text
1
income_certificate.json
Show more lines

Passport:

Plain Text
1
passport.json
Show more lines

Future:

Plain Text
1
driving_license.json
2
 
3
pension.json
Show more lines

Benefits:

Plain Text
1
Add Metadata
2
 
3
↓
4
 
5
New Service
Show more lines

No UI rewrite.

Slide 26: End-to-End Income Flow
Plain Text
1
User
2
 
3
↓
4
 
5
Home Screen
6
 
7
↓
8
 
9
Income Certificate
10
 
11
↓
12
 
13
ADK Service Selection
14
 
15
↓
16
 
17
income_certificate.json
18
 
19
↓
20
 
21
Dynamic UI
22
 
23
↓
24
 
25
Submit
26
 
27
↓
28
 
29
AppFunctionRegistry
30
 
31
↓
32
 
33
SubmitApplicationUseCase
34
 
35
↓
36
 
37
DynamicWorkflowEngine
38
 
39
↓
40
 
41
EligibilityAgent
42
 
43
↓
44
 
45
VerificationAgent
46
 
47
↓
48
 
49
ApprovalAgent
50
 
51
↓
52
 
53
Repository
54
 
55
↓
56
 
57
API
Show more lines
Slide 27: End-to-End Passport Flow
Plain Text
1
User
2
 
3
↓
4
 
5
Passport
6
 
7
↓
8
 
9
passport.json
10
 
11
↓
12
 
13
Dynamic UI
14
 
15
↓
16
 
17
Submit
18
 
19
↓
20
 
21
AppFunctionRegistry
22
 
23
↓
24
 
25
SubmitPassportUseCase
26
 
27
↓
28
 
29
DynamicWorkflowEngine
30
 
31
↓
32
 
33
DocumentCheckAgent
34
 
35
↓
36
 
37
PoliceVerificationAgent
38
 
39
↓
40
 
41
PassportApprovalAgent
42
 
43
↓
44
 
45
Repository
46
 
47
↓
48
 
49
API
Show more lines
Slide 28: Benefits
Technical

✅ Clean Architecture

✅ MVVM

✅ Hilt

✅ Reusable UI

✅ Dynamic Workflow

✅ AI Ready

✅ ADK Ready

✅ AppFunctions Ready

Business

✅ Faster onboarding

✅ Reusable framework

✅ Reduced development effort

✅ Easy service expansion

Slide 29: Current Limitations
ADK
Service identification only
Workflow still partially metadata driven
App Functions
Not yet integrated with real Android Intelligence/Gemini runtime
APIs
Using mock APIs
Not connected to actual government systems
Services

Currently:

Plain Text
1
Income Certificate
2
 
3
Passport
Show more lines

Only.

Slide 30: Future Roadmap
Phase 1 ✅

Income Certificate

Passport

Phase 2

Driving License

Pension

Phase 3

ADK Workflow Planner

Plain Text
1
Generate workflows dynamically
Show more lines
Phase 4

Gemini Generated Forms

Plain Text
1
No static JSON
Show more lines
Phase 5

Real Government APIs

Final Closing Statement

GovernmentServicePOC is not a standalone Income Certificate or Passport application. It is a reusable, AI-ready Government Service Platform built using Clean Architecture, MVVM, Hilt, A2UI for dynamic UI generation, AGenUI for agent-based workflow orchestration, Google ADK for intelligent service identification, and Android App Functions for future AI interoperability. New services such as Driving License and Pension can be onboarded with minimal changes by introducing metadata, workflows, and agents while reusing the same platform foundation.
