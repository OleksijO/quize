# quize


<b>The application represents system of tests for tutors and students.</b>
<hr>

Anybody could register in system as Tutor or Student.
<hr>

Tutor could:
 
  - create, edit or delete (unsubscribed) Subject.

  - create, edit or delete test on created by him/her Subject.

  - view students statistics on his/her Subjects
 
  - view average statistics on Subjects
<hr>

Student could:
  
  - subscribe/unsubscribe to Subjects
  
  - pass tests on subscribed Subjects
  
  - view his/her statistics on Subjects (average level)
  
  - view statistics on Subjects (average level)
<hr>

<b>Domain</b>
<ul>
  <li><b>User</b></i>
  <ul>
    <li>Email (Login)</li>
    <li>Password</li>
    <li>Role (Tutor, Student)</li>
    <li>First Name</li>
    <li>Last Name</li>
  </ul>
  
  <li><b>Subject</b></i>
  <ul>
    <li>Name</li>
    <li>Tests</li>
    <li>Tutor</li>
    <li>Students</li>
  </ul>
  <li><b>Test</b></li>
  <ul>
    <li>Subject</li>
    <li><b>Test Item</b></li>
      <ul>
        <li>Question (text)</li>
        <li><b>Answers</b></li>
            <ul>
                <li>text</li>
                <li>is Correct</li>
            </ul>
       </ul>
    <li>Tutor</li>
    <li>Available since date </li>
    <li>Available until date </li>
    <li>Pass Threshold</li>
  </ul>
  <li><b>Test Results</b></li>
  <ul>
    <li>Student</li>
    <li>Test</>
    <li>Attempt #</li>
    <li>Date Time</li>
    <li>Average level</li>
  </ul>
</ul>

<hr>
<b>UI</b>
Pages:
<ul>
  <li>Welcome with subjects with avg level</li>
  <li>Create/Update/Delete Subject</li>
  <li>Create/Update/Delete Test</li>
  <li>Tests with avg levels on Subject</li>
  <li>Pass Test</li>
  <li>Student's results</li>
  <li>Subject students' results</li>
</ul>
<hr>
<b>Technology stack:</b>
<ul>
  <li>Spring Core</li>
  <li>Spring Boot</li>
  <li>Spring Data/RestData</li>
  <li>Spring Security</li>
  <li>Hibernate</li>
  <li>JS/ReactJS</li>
  <li>// JMS</li>
</ul>
