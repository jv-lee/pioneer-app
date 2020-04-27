import 'package:flutter/material.dart';

class RecommendPage extends StatefulWidget{
  @override
  State<StatefulWidget> createState() {
    return RecommendState();
  }
}

class RecommendState extends State<RecommendPage>{
  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Container(
        color: Colors.grey,
        child: Center(
          child: Text('this is RecommendPage.'),
      ),
    );
  }
}