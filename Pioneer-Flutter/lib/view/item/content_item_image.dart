import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';
import 'package:pioneer_flutter/constants/http_constants.dart';
import 'package:pioneer_flutter/theme/theme_dimens.dart';

/// @author jv.lee
/// @date 2020/5/19
/// @description
class ContentItemImage extends StatefulWidget {
  ContentItemImage(this.path, {this.isSingle}) : super();
  final String path;
  final isSingle;

  @override
  _ContentItemImageState createState() => _ContentItemImageState();
}

class _ContentItemImageState extends State<ContentItemImage> {
  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    if (widget.path == null || widget.path == "null" || widget.path == "") {
      return buildEmpty(context);
    } else {
      return buildNetworkImage(context);
    }
  }

  Widget buildNetworkImage(BuildContext context) {
    return Container(
        height: ThemeDimens.item_content_picture_height,
        width: ThemeDimens.item_content_picture_width,
        margin: EdgeInsets.only(
            right: widget.isSingle ? ThemeDimens.margin_medium : 0),
        child: CachedNetworkImage(
          imageUrl: HttpConstants.getCropImagePath(widget.path),
          imageBuilder: (context, imageProvider) => Container(
            decoration: BoxDecoration(
              borderRadius: BorderRadius.all(
                  Radius.circular(ThemeDimens.item_content_picture_radius)),
              image: DecorationImage(image: imageProvider, fit: BoxFit.cover),
            ),
          ),
          placeholder: (context, url) => Container(
            decoration: BoxDecoration(
                color: Theme.of(context).primaryColor,
                borderRadius: BorderRadius.all(
                    Radius.circular(ThemeDimens.item_content_picture_radius))),
          ),
          errorWidget: (context, url, error) => Icon(Icons.error),
        ));
  }

  Widget buildImage(BuildContext context) {
    return Container(
      margin: EdgeInsets.only(
          right: widget.isSingle ? ThemeDimens.margin_medium : 0),
      height: ThemeDimens.item_content_picture_height,
      width: ThemeDimens.item_content_picture_width,
      decoration: BoxDecoration(
          color: Theme.of(context).primaryColor,
          borderRadius: BorderRadius.all(
              Radius.circular(ThemeDimens.item_content_picture_radius)),
          image: DecorationImage(
              image: NetworkImage(HttpConstants.getCropImagePath(widget.path)),
              fit: BoxFit.cover)),
    );
  }

  Widget buildEmpty(BuildContext context) {
    return Container(
        margin: EdgeInsets.only(
            right: widget.isSingle ? ThemeDimens.margin_medium : 0),
        height: ThemeDimens.item_content_picture_height,
        width: ThemeDimens.item_content_picture_width,
        decoration: BoxDecoration(
            color: Theme.of(context).canvasColor,
            borderRadius: BorderRadius.all(
                Radius.circular(ThemeDimens.item_content_picture_radius))));
  }
}
