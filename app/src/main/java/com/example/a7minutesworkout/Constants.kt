package com.example.a7minutesworkout

object Constants {

    fun defaultExerciseList(): ArrayList<ExerciseModel>{
        val exerciseList = ArrayList<ExerciseModel>()
        val jumpingJacks = ExerciseModel(1,"Jumping Jacks",R.drawable.ic_jumping_jacks,false,false)
        exerciseList.add(jumpingJacks)

        val lunge = ExerciseModel(2,"Lunge Exercise",R.drawable.ic_lunge,false,false)
        exerciseList.add(lunge)

        val plank = ExerciseModel(3,"Plank Exercise",R.drawable.ic_plank,false,false)
        exerciseList.add(plank)

        val pushUp = ExerciseModel(4,"Push Up",R.drawable.ic_push_up,false,false)
        exerciseList.add(pushUp)

        val pushUpAndRotation = ExerciseModel(5,"Push Up And Rotation",R.drawable.ic_push_up_and_rotation,false,false)
        exerciseList.add(pushUpAndRotation)

        val sidePlank = ExerciseModel(6,"Side Plank Exercise",R.drawable.ic_side_plank,false,false)
        exerciseList.add(sidePlank)

        val squat = ExerciseModel(7,"Squat Exercise",R.drawable.ic_squat,false,false)
        exerciseList.add(squat)

        val stepUp = ExerciseModel(8,"Step Up onto Chair",R.drawable.ic_step_up_onto_chair,false,false)
        exerciseList.add(stepUp)

        val triceps = ExerciseModel(9,"Triceps Dip On Chair",R.drawable.ic_triceps_dip_on_chair,false,false)
        exerciseList.add(triceps)

        val wallSit = ExerciseModel(10,"Wall Sit Exercise",R.drawable.ic_wall_sit,false,false)
        exerciseList.add(wallSit)
        return exerciseList
    }
}