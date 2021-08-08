<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.87.0">
    <title>videocheckout</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.1/examples/cheatsheet/">

    

    <!-- Bootstrap core CSS -->
<link href="../assets/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>

    
    <!-- Custom styles for this template -->
    <link href="cheatsheet.css" rel="stylesheet">
  </head>
  <body class="bg-light">
    
<header class="bd-header bg-white py-3 d-flex align-items-stretch border-bottom border-dark">
  <div class="container-fluid d-flex align-items-center">
    <h1 class="d-flex align-items-center fs-4 text-dark mb-0">
      音视频审核
    </h1>
  </div>
</header>
<aside class="bd-aside sticky-xl-top text-muted align-self-start mb-3 mb-xl-5 px-2">
  <h2 class="h6 pt-4 pb-3 mb-4 border-bottom">审核结果</h2>
  <nav class="small" id="toc">
    <ul class="list-unstyled">
      <li class="my-2">
        <button class="btn d-inline-flex align-items-center collapsed" data-bs-toggle="collapse" aria-expanded="false" data-bs-target="#videoinfo" aria-controls="videoinfo">视频信息</button>
        <ul class="list-unstyled ps-3 collapse" id="videoinfo">
          <li><a class="d-inline-flex align-items-center rounded" >视频名称：</a><a id="videoname">id</a></li>
          <li><a class="d-inline-flex align-items-center rounded" >视频时长：</a><a id="videotime">time</a></li>
        </ul>
      </li>
      <li class="my-2">
        <button class="btn d-inline-flex align-items-center collapsed" data-bs-toggle="collapse" aria-expanded="false" data-bs-target="#videorel" aria-controls="videorel">视频结果</button>
        <ul class="list-unstyled ps-3 collapse" id="videorel">
          <li><a class="d-inline-flex align-items-center rounded" id="videofin">成功</a></li>
        </ul>
      </li>
      <li class="my-2">
        <button class="btn d-inline-flex align-items-center collapsed" data-bs-toggle="collapse" aria-expanded="false" data-bs-target="#audiorel" aria-controls="audiorel">音频结果</button>
        <ul class="list-unstyled ps-3 collapse" id="audiorel">
          <li><a class="d-inline-flex align-items-center rounded" id="rudiofin">成功</a></li>
          <li><a class="d-inline-flex align-items-center rounded" >违规词：</a><a id="viowords">id</a></li>
        </ul>
      </li>
      <li class="my-2">
        <button class="btn d-inline-flex align-items-center collapsed" data-bs-toggle="collapse" aria-expanded="false" data-bs-target="#humanrel" aria-controls="humanrel">人工标记</button>
        <ul class="list-unstyled ps-3 collapse" id="humanrel">
          <div class="btn-group-vertical" role="group" aria-label="Basic mixed styles example">

            <input type="button" class="btn btn-success btn-group-lg" onclick="passVideo()" value="通过"></input>
            <input type="button" class="btn btn-secondary btn-group-lg" onclick="faleVideo()" value="不通过"></input>
          </div>
        </ul>
      </li>
    </ul>
  </nav>
</aside>
<div class="bd-cheatsheet container-fluid bg-body">
  <section id="forms">
    <!-- <h2 class="sticky-xl-top fw-bold pt-3 pt-xl-5 pb-2 pb-xl-3">上传</h2> -->
    <article class="my-3" id="overview">
      <div class="bd-heading sticky-xl-top align-self-start mt-5 mb-3 mt-xl-0 mb-xl-2">
        <h3 class="d-flex align-items-center fs-4 text-white mb-0">上视频传</h3>
      </div>

      <div>
        <div class="bd-example">
        <form>
          <div class="mb-3">
            <label class="form-label" for="customFile">Upload</label>
            <input type="file" class="form-control" accept = "video/*" id="customFile">
          </div>
          <button type="submit" class="btn btn-primary" onclick="submitVideo()">Submit</button>
        </form>
        </div>
      </div>
    </article>

    
  </section>
  <section id="content">
    <!-- <h2 class="sticky-xl-top fw-bold pt-3 pt-xl-5 pb-2 pb-xl-3">审核列表</h2> -->
    <article class="my-3" id="video">
      <div class="bd-heading sticky-xl-top align-self-start mt-5 mb-3 mt-xl-0 mb-xl-2">
        <h3 class="d-flex align-items-center fs-4 text-white mb-0">原视频</h3>
      </div>
      <div class="embed-responsive embed-responsive-16by9">
        <video width="540" height="360" controls>
        <source src="../movie.mp4" type="video/mp4">
        <!-- <source src="../IMG_0720.MOV" type="video/quicktime"> -->
        </video>
      </div>
    </article>
    <article class="my-3" id="images">
      <div class="bd-heading sticky-xl-top align-self-start mt-5 mb-3 mt-xl-0 mb-xl-2">
        <h3 class="d-flex align-items-center fs-4 text-white mb-0">视频帧</h3>
      </div>
      <div>
        
        <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
          <div class="carousel-indicators">
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
          </div>
          <div class="carousel-inner">
            <div class="carousel-item active">
              <img src="../image-2.jpg" class="img-thumbnail" alt="First slide">
              <img src="../image-2.jpg" class="img-thumbnail" alt="First slide">
              <img src="../image-3.jpg" class="img-thumbnail" alt="First slide">
            </div>
            <div class="carousel-item">
              <img src="../image-4.jpg" class="img-thumbnail" alt="Second slide">
              <img src="../image-5.jpg" class="img-thumbnail" alt="First slide">
              <img src="../image-6.jpg" class="img-thumbnail" alt="First slide">
            </div>
            <div class="carousel-item">
              <img src="../image-7.jpg" class="img-thumbnail" alt="Third slide">
              <img src="../image-8.jpg" class="img-thumbnail" alt="First slide">
              <img src="../image-9.jpg" class="img-thumbnail" alt="First slide">
            </div>
          </div>
          <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
          </button>
          <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
          </button>
        </div>
      </div>
    </article>
    
    <article class="my-3" id="figures">
      <div class="bd-heading sticky-xl-top align-self-start mt-5 mb-3 mt-xl-0 mb-xl-2">
        <h3 class="d-flex align-items-center fs-4 text-white mb-0">文字</h3>
      </div>

      <div>
        <div class="bd-example">
          <div class="form-floating">
            <textarea class="form-control" placeholder="Leave a comment here" id="floatingTextarea2" style="height: 100px"></textarea>
            <label for="floatingTextarea2">音频文字</label>
          </div>
        </div>
      </div>
    </article>
  </section>



</div>

<div class="modal fade" id="exampleModalDefault" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="staticBackdropLive" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLiveLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLiveLabel">Modal title</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <p>I will not close if you click outside me. Don't even try to press escape key.</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Understood</button>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="exampleModalCenteredScrollable" tabindex="-1" aria-labelledby="exampleModalCenteredScrollableTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalCenteredScrollableTitle">Modal title</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <p>This is some placeholder content to show the scrolling behavior for modals. We use repeated line breaks to demonstrate how content can exceed minimum inner height, thereby showing inner scrolling. When content becomes longer than the prefedined max-height of modal, content will be cropped and scrollable within the modal.</p>
        <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
        <p>This content should appear at the bottom after you scroll.</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="exampleModalFullscreen" tabindex="-1" aria-labelledby="exampleModalFullscreenLabel" aria-hidden="true">
  <div class="modal-dialog modal-fullscreen">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title h4" id="exampleModalFullscreenLabel">Full screen modal</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>


    <script src="../assets/dist/js/bootstrap.bundle.min.js"></script>

    <script src="cheatsheet.js"></script>
    <script src="sub.js"></script>
  </body>
</html>
