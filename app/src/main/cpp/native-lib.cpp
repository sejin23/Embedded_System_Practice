#include <jni.h>
#include <unistd.h>
#include <fcntl.h>
#include <string>
#include <sys/ioctl.h>

#define WRITETEXT       322
#define SETNEXTBLOCK    323

extern "C" JNIEXPORT void JNICALL
Java_edu_skku_sejin_tetris_1game_MainActivity_sendStringtoJNI(JNIEnv *env, jobject obj, jstring jstr) {
    const char *str = (*env).GetStringUTFChars(jstr, 0);
    int i;
    char buf[7];
    memset(buf, 0, 7);
    for(i = 0; i < 7; i++) {
        if(strlen(str) <= i) buf[6 - i] = 0;
        else buf[6 - i] = str[strlen(str) - 1 - i];
    }
    syscall(WRITETEXT, buf);
    return;
}

extern "C" JNIEXPORT void JNICALL
Java_edu_skku_sejin_tetris_1game_TetrisView_sendScoretoJNI(JNIEnv *env, jobject obj, jint score) {
    int num = score;
    char buf[7];
    memset(buf, 0, 7);
    for(int i = 0; i < 7; i++) {
        buf[6 - i] = '0' + (char)(num % 10);
        num /= 10;
    }
    syscall(WRITETEXT, buf);
    return;
}

extern "C" JNIEXPORT void JNICALL
Java_edu_skku_sejin_tetris_1game_TetrisView_setnextledtoJNI(JNIEnv *env, jobject obj, jcharArray param) {
    jsize len = (*env).GetArrayLength(param);
    jchar *buf = (*env).GetCharArrayElements(param, NULL);
    char led[7];
    for(int i = 0; i < len; i++)
        led[i] = buf[i];

    syscall(SETNEXTBLOCK, led);
    return;
}