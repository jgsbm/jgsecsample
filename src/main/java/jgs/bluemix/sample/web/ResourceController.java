package jgs.bluemix.sample.web;

import jgs.bluemix.sample.exception.ResourceNotFoundException;
import jgs.bluemix.sample.entity.ProductPic;
import jgs.bluemix.sample.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 様々なリソースの取得に利用するControllerです。
 *
 * @author ryozo
 */
@RestController
@RequestMapping("resource")
public class ResourceController {

    @Autowired
    ProductService productService;

    /**
     * URLに指定された商品画像(JPG)を取得します.
     *
     * @param productCode 画像取得対象商品のコード(URLより取得)
     * @return JPG画像
     * @throws IOException
     */
    @RequestMapping("product/{productCode}.jpg")
    @ResponseBody
    public ResponseEntity<byte[]> jpgProductResource(@PathVariable String productCode) throws IOException {
        ProductPic pic = productService.findProductPic(productCode);
        if (pic == null || isEmptyResouce(pic.getPic())) {
            throw new ResourceNotFoundException();
        }

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<byte[]>(pic.getPic(), headers, HttpStatus.OK);
    }

    /**
     * 引数のbyte配列が空であるか判定します.
     * @param bytes 判定対象のbyte配列
     * @return 判定結果
     */
    private boolean isEmptyResouce(byte[] bytes) {
        return bytes == null || bytes.length == 0;
    }
}
