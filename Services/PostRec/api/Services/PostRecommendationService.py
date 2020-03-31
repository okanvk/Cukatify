
from tensorflow.keras.models import load_model
from random import choice
import pandas as pd

class PostRecommender:

    def __init__(self):
        self.post_model = load_model("api/Services/postrec_model.h5")
        self.df = pd.read_csv('api/Services/userpostratings.csv')
        self.df = self.df[['_id', 'userId', 'postId', 'rating']]
        self.df.drop("_id", axis=1)
        self.users_items_matrix_df = self.df.pivot(index='userId',
                                         columns='postId',
                                         values='rating').fillna(0)
        self.X = self.users_items_matrix_df.values
        self.y = self.users_items_matrix_df.values
        self.new_matrix = self.post_model.predict(self.X) * (self.X == 0)
        self.new_users_items_matrix_df = pd.DataFrame(self.new_matrix,
                                                 columns=self.users_items_matrix_df.columns,
                                                 index=self.users_items_matrix_df.index)

    def _recommender_for_user(self,user_id, topn=5):

        pred_scores = self.new_users_items_matrix_df.loc[user_id].values

        df_scores = pd.DataFrame({'content_id': list(self.users_items_matrix_df.columns),
                                  'score': pred_scores})

        df_rec = df_scores.set_index('content_id') \
            .sort_values('score', ascending=False) \
            .head(topn)[['score']]

        return df_rec[df_rec.score > 0].index.values.tolist()

    def recommend_3_post(self, user_id):
        post_ids = self._recommender_for_user(user_id)
        post_3 = self.__select3Post__(post_ids)
        return post_3


    def __select3Post__(self,list):
        sec_list = []
        while True:
            if len(sec_list) == 3:
                break

            id = choice(list)

            if id not in sec_list:
                sec_list.append(id)

        return sec_list


