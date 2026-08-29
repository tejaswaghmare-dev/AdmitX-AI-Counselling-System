package com.admitx.dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import com.admitx.config.FirebaseConfig;
import com.admitx.model.Notice;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;

public class NoticeDAO {

    private final Firestore db =
            new FirebaseConfig().getFirestore();

    // =========================================================
    // CREATE NOTICE
    // =========================================================

    public boolean createNotice(
            String title,
            String description,
            String tag
    ) {

        try {

            String noticeId =
                    UUID.randomUUID()
                            .toString();

            Notice notice =
                    new Notice(
                            noticeId,
                            title,
                            description,
                            tag,
                            true,
                            System.currentTimeMillis()
                    );

            db.collection("Notices")
                    .document(noticeId)
                    .set(notice)
                    .get();

            System.out.println(
                    "Notice created successfully."
            );

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // UPDATE NOTICE
    // =========================================================

    public boolean updateNotice(
            Notice notice
    ) {

        try {

            if (
                    notice == null
                    ||
                    notice.getNoticeId() == null
                    ||
                    notice.getNoticeId().isBlank()
            ) {

                return false;
            }

            db.collection("Notices")
                    .document(
                            notice.getNoticeId()
                    )
                    .set(notice)
                    .get();

            System.out.println(
                    "Notice updated successfully."
            );

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // DELETE NOTICE
    // =========================================================

    public boolean deleteNotice(
            String noticeId
    ) {

        try {

            if (
                    noticeId == null
                    ||
                    noticeId.isBlank()
            ) {

                return false;
            }

            db.collection("Notices")
                    .document(noticeId)
                    .delete()
                    .get();

            System.out.println(
                    "Notice deleted successfully."
            );

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // GET ALL NOTICES
    // =========================================================

    public List<Notice> getAllNotices() {

        List<Notice> notices =
                new ArrayList<>();

        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection("Notices")
                            .get();

            QuerySnapshot snapshot =
                    future.get();

            for (
                    DocumentSnapshot document :
                    snapshot.getDocuments()
            ) {

                if (!document.exists()) {
                    continue;
                }

                Notice notice =
                        document.toObject(
                                Notice.class
                        );

                if (notice == null) {
                    continue;
                }

                if (
                        notice.getNoticeId() == null
                        ||
                        notice.getNoticeId().isBlank()
                ) {

                    notice.setNoticeId(
                            document.getId()
                    );
                }

                notices.add(
                        notice
                );
            }

            // Latest notice first
            notices.sort(
                    Comparator.comparingLong(
                            Notice::getCreatedAt
                    ).reversed()
            );

        } catch (Exception e) {

            e.printStackTrace();
        }

        return notices;
    }

    // =========================================================
    // GET PUBLISHED NOTICES FOR STUDENT
    // =========================================================

    public List<Notice> getPublishedNotices() {

        List<Notice> publishedNotices =
                new ArrayList<>();

        for (
                Notice notice :
                getAllNotices()
        ) {

            if (
                    notice.isPublished()
            ) {

                publishedNotices.add(
                        notice
                );
            }
        }

        return publishedNotices;
    }

    // =========================================================
    // NOTICE COUNT
    // =========================================================

    public int getNoticeCount() {

        return getAllNotices()
                .size();
    }
}