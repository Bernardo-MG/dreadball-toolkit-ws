 <!--
    Copyright 2015 the original author or authors

    Licensed under the Apache License, Version 2.0 (the "License"); you may not
    use this file except in compliance with the License. You may obtain a copy of
    the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
    WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
    License for the specific language governing permissions and limitations under
    the License.
 -->
 <!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html); charset=utf-8"/>
    <meta charset='utf-8'>
    
    <title>Team Assets Availability ${model.id} detailed information</title>
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <link href="../../static/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="../../static/css/theme.css" rel="stylesheet">
</head>
<body>
<section class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Team Assets Availability ${model.id}</h3>
                </div>
                <div class="panel-body">
                    <dl class="dl-horizontal">
                        <dt>Id</dt>
                        <dd>${model.id}</dd>
                        <dt>Team</dt>
                        <dd>${model.teamType.name}</dd>
                        <dt>Cheerleader cost</dt>
                        <dd>${model.cheerleaderCost}</dd>
                        <dt>Cheerleader initial</dt>
                        <dd>${model.cheerleaderInitial}</dd>
                        <dt>Cheerleader initial</dt>
                        <dd>${model.cheerleaderMax}</dd>
                        <dt>Coaching Die cost</dt>
                        <dd>${model.coachingDieCost}</dd>
                        <dt>Coaching Die initial</dt>
                        <dd>${model.coachingDieInitial}</dd>
                        <dt>Coaching Die initial</dt>
                        <dd>${model.coachingDieMax}</dd>
                        <dt>Coaching Staff cost</dt>
                        <dd>${model.coachingStaffCost}</dd>
                        <dt>Dreadball Card cost</dt>
                        <dd>${model.dreadballCardCost}</dd>
                        <dt>Dreadball Card initial</dt>
                        <dd>${model.dreadballCardInitial}</dd>
                        <dt>Dreadball Card initial</dt>
                        <dd>${model.dreadballCardMax}</dd>
                        <dt>Defensive Coaching Staff</dt>
                        <dd>${model.isStartingWithDefensiveCoachingStaff()?c}</dd>
                        <dt>Offensive Coaching Staff</dt>
                        <dd>${model.isStartingWithOffensiveCoachingStaff()?c}</dd>
                        <dt>Support Coaching Staff</dt>
                        <dd>${model.isStartingWithSupportCoachingStaff()?c}</dd>
                    </dl>
                </div>
            </div>
        </div>
    </div>
</section>
<script src="../../static/lib/jquery/jquery-1.11.3.min.js"></script>
<script src="../../static/lib/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>