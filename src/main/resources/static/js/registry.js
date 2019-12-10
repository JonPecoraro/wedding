$(function() {
	common.getImageFromS3('registry/zola-scaled.png', $('#zola'), 'Zola', 'card-img-top p-5', true);
	common.getImageFromS3('registry/amazon-scaled.png', $('#amazon'), 'Amazon', 'card-img-top p-5', true);
	common.getImageFromS3('registry/williams-sonoma-scaled.png', $('#williams-sonoma'), 'Williams Sonoma', 'card-img-top p-5', true);
	common.getImageFromS3('registry/pottery-barn-scaled.png', $('#pottery-barn'), 'Pottery Barn', 'card-img-top p-5', true);
});